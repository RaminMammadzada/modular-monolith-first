package com.medflow.application.usecase;

import com.medflow.application.ports.ClinicPortBundle;
import com.medflow.domain.appointment.*;
import com.medflow.domain.audit.AuditEvent;
import com.medflow.domain.clinician.ClinicianId;
import com.medflow.domain.common.DomainException;
import com.medflow.domain.intake.*;
import com.medflow.domain.lab.*;
import com.medflow.domain.patient.*;
import com.medflow.domain.timeline.TimelineEvent;
import com.medflow.domain.triage.*;
import java.time.*;
import java.util.*;

public class MedFlowService {
    private final ClinicPortBundle.Patients patients;
    private final ClinicPortBundle.Intakes intakes;
    private final ClinicPortBundle.Triages triages;
    private final ClinicPortBundle.Appointments appointments;
    private final ClinicPortBundle.Labs labs;
    private final ClinicPortBundle.Notifications notifications;
    private final ClinicPortBundle.Audit audit;
    private final ClinicPortBundle.Timeline timeline;

    public MedFlowService(
            ClinicPortBundle.Patients patients,
            ClinicPortBundle.Intakes intakes,
            ClinicPortBundle.Triages triages,
            ClinicPortBundle.Appointments appointments,
            ClinicPortBundle.Labs labs,
            ClinicPortBundle.Notifications notifications,
            ClinicPortBundle.Audit audit,
            ClinicPortBundle.Timeline timeline) {
        this.patients = patients; this.intakes = intakes; this.triages = triages; this.appointments = appointments;
        this.labs = labs; this.notifications = notifications; this.audit = audit; this.timeline = timeline;
    }

    public Patient registerPatient(String given, String family, String email, String phone, LocalDate dob) {
        var p = new Patient(PatientId.newId(), new PatientName(given, family), new EmailAddress(email), new PhoneNumber(phone), new DateOfBirth(dob));
        patients.save(p);
        appendAudit("patient.registered", Map.of("patientId", p.id().value().toString()));
        timeline.append(p.id(), new TimelineEvent("PATIENT_REGISTERED", p.name().fullName(), Instant.now()));
        return p;
    }

    public Patient updatePatientContact(UUID patientId, String email, String phone) {
        var existing = patients.findById(new PatientId(patientId)).orElseThrow(() -> new DomainException("patient not found"));
        var updated = existing.updateContact(new EmailAddress(email), new PhoneNumber(phone));
        patients.save(updated);
        appendAudit("patient.contact.updated", Map.of("patientId", patientId.toString()));
        return updated;
    }

    public SymptomIntake submitIntake(UUID patientId, List<String> symptoms) {
        var intake = new SymptomIntake(IntakeId.newId(), new PatientId(patientId), symptoms, Instant.now());
        intakes.save(intake);
        appendAudit("intake.submitted", Map.of("intakeId", intake.id().value().toString()));
        timeline.append(intake.patientId(), new TimelineEvent("INTAKE_SUBMITTED", String.join(",", symptoms), intake.submittedAt()));
        return intake;
    }

    public TriageCase evaluateTriage(UUID patientId, UUID intakeId) {
        var intake = intakes.findById(new IntakeId(intakeId)).orElseThrow(() -> new DomainException("intake not found"));
        TriageOutcome outcome = classify(intake.symptoms());
        var triageCase = new TriageCase(TriageCaseId.newId(), new PatientId(patientId), intake.id(), outcome, Instant.now());
        triages.save(triageCase);
        appendAudit("triage.evaluated", Map.of("triageCaseId", triageCase.id().value().toString()));
        timeline.append(triageCase.patientId(), new TimelineEvent("TRIAGE_EVALUATED", outcome.reason(), triageCase.evaluatedAt()));
        return triageCase;
    }

    private TriageOutcome classify(List<String> symptoms) {
        var s = String.join(" ", symptoms).toLowerCase(Locale.ROOT);
        if (s.contains("chest pain") || s.contains("severe bleeding")) return new EmergencyOutcome("high-risk synthetic pattern");
        if (s.contains("high fever")) return new UrgentOutcome("same-day review", Duration.ofHours(6));
        if (s.contains("cough")) return new RoutineOutcome("routine follow-up");
        return new SelfCareOutcome("monitor symptoms and re-check");
    }

    public List<AppointmentSlot> searchSlots(Instant from, String specialty) {
        return appointments.findSlots(from, specialty);
    }

    public Appointment bookAppointment(UUID patientId, Instant startsAt, String specialty, TriageUrgency urgency) {
        if (urgency == TriageUrgency.EMERGENCY) throw new DomainException("emergency cases cannot be standard booking");
        var slot = searchSlots(startsAt, specialty).stream().findFirst().orElseThrow(() -> new DomainException("no slots"));
        var appointment = new Appointment(AppointmentId.newId(), new PatientId(patientId), slot, urgency, Instant.now(), false);
        appointments.save(appointment);
        notifications.send(patientId, "Appointment booked: " + slot.startsAt());
        timeline.append(new PatientId(patientId), new TimelineEvent("APPOINTMENT_BOOKED", slot.startsAt().toString(), appointment.bookedAt()));
        appendAudit("appointment.booked", Map.of("appointmentId", appointment.id().value().toString()));
        return appointment;
    }

    public void cancelAppointment(UUID appointmentId) {
        var current = appointments.findById(new AppointmentId(appointmentId)).orElseThrow(() -> new DomainException("appointment not found"));
        appointments.save(current.cancel());
        appendAudit("appointment.cancelled", Map.of("appointmentId", appointmentId.toString()));
    }

    public LabOrder createLabOrder(UUID patientId, String testName) {
        var order = new LabOrder(LabOrderId.newId(), new PatientId(patientId), testName, Instant.now(), "ORDERED");
        labs.saveOrder(order);
        timeline.append(new PatientId(patientId), new TimelineEvent("LAB_ORDERED", testName, order.orderedAt()));
        appendAudit("lab.order.created", Map.of("labOrderId", order.id().value().toString()));
        return order;
    }

    public LabResult attachLabResult(UUID labOrderId, String value) {
        var order = labs.findOrder(new LabOrderId(labOrderId)).orElseThrow(() -> new DomainException("lab order not found"));
        if ("CANCELLED".equals(order.status())) throw new DomainException("cannot attach result to cancelled order");
        var result = new LabResult(order.id(), value, "ATTACHED", Instant.now());
        labs.saveResult(result);
        timeline.append(order.patientId(), new TimelineEvent("LAB_RESULT_ATTACHED", value, result.attachedAt()));
        appendAudit("lab.result.attached", Map.of("labOrderId", labOrderId.toString()));
        return result;
    }

    public List<TimelineEvent> patientTimeline(UUID patientId) { return timeline.forPatient(new PatientId(patientId)); }
    public List<AuditEvent> auditTrail() { return audit.list(); }

    private void appendAudit(String type, Map<String, String> metadata) {
        audit.write(new AuditEvent(UUID.randomUUID(), type, UUID.randomUUID(), Instant.now(), metadata));
    }

    public Map<String, Long> clinicianDashboard() {
        return Map.of("appointmentsToday", (long) searchSlots(Instant.now(), "general").size(), "openLabOrders", 0L);
    }
}
