package com.medflow.application;

import static org.junit.jupiter.api.Assertions.*;

import com.medflow.application.ports.ClinicPortBundle;
import com.medflow.application.usecase.MedFlowService;
import com.medflow.domain.appointment.*;
import com.medflow.domain.audit.AuditEvent;
import com.medflow.domain.intake.*;
import com.medflow.domain.lab.*;
import com.medflow.domain.patient.*;
import com.medflow.domain.timeline.TimelineEvent;
import com.medflow.domain.triage.TriageCase;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Test;

class MedFlowServiceTest {
    @Test
    void endToEndCoreFlowWorks() {
        var svc = new MedFlowService(new P(), new I(), new T(), new A(), new L(), (id, msg) -> {}, new Au(), new Tl());
        var p = svc.registerPatient("Ada", "Patient", "ada@example.test", "+12025550101", LocalDate.of(1991, 2, 3));
        var intake = svc.submitIntake(p.id().value(), List.of("cough"));
        var triage = svc.evaluateTriage(p.id().value(), intake.id().value());
        assertNotNull(triage.id());
    }

    static class P implements ClinicPortBundle.Patients { Map<UUID, Patient> m = new HashMap<>(); public void save(Patient p){m.put(p.id().value(),p);} public Optional<Patient> findById(PatientId id){return Optional.ofNullable(m.get(id.value()));}}
    static class I implements ClinicPortBundle.Intakes { Map<UUID, SymptomIntake> m = new HashMap<>(); public void save(SymptomIntake p){m.put(p.id().value(),p);} public Optional<SymptomIntake> findById(IntakeId id){return Optional.ofNullable(m.get(id.value()));}}
    static class T implements ClinicPortBundle.Triages { Map<UUID, TriageCase> m = new HashMap<>(); public void save(TriageCase p){m.put(p.id().value(),p);} public Optional<TriageCase> findById(com.medflow.domain.triage.TriageCaseId id){return Optional.ofNullable(m.get(id.value()));}}
    static class A implements ClinicPortBundle.Appointments { public void save(Appointment p){} public Optional<Appointment> findById(AppointmentId id){return Optional.empty();} public List<AppointmentSlot> findSlots(Instant from,String specialty){return List.of(new AppointmentSlot(new com.medflow.domain.clinician.ClinicianId(UUID.randomUUID()),from,from.plusSeconds(3600),specialty));}}
    static class L implements ClinicPortBundle.Labs { public void saveOrder(LabOrder o){} public Optional<LabOrder> findOrder(LabOrderId id){return Optional.empty();} public void saveResult(LabResult r){} public Optional<LabResult> findResult(LabOrderId id){return Optional.empty();}}
    static class Au implements ClinicPortBundle.Audit { public void write(AuditEvent event){} public List<AuditEvent> list(){return List.of();}}
    static class Tl implements ClinicPortBundle.Timeline { public void append(PatientId id, TimelineEvent e){} public List<TimelineEvent> forPatient(PatientId id){return List.of();}}
}
