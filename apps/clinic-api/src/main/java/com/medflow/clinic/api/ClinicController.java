package com.medflow.clinic.api;

import com.medflow.application.usecase.MedFlowService;
import com.medflow.domain.triage.TriageUrgency;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api")
public class ClinicController {
    private final MedFlowService service;
    public ClinicController(MedFlowService service) { this.service = service; }

    @PostMapping("/patients")
    Map<String, String> registerPatient(@RequestBody @Valid RegisterPatientRequest req) {
        var p = service.registerPatient(req.givenName(), req.familyName(), req.email(), req.phone(), req.dateOfBirth());
        return Map.of("patientId", p.id().value().toString());
    }

    @GetMapping("/patients/{patientId}")
    Map<String, String> getPatient(@PathVariable UUID patientId) {
        return Map.of("patientId", patientId.toString(), "status", "lookup through timeline endpoint in this scaffold");
    }

    @PatchMapping("/patients/{patientId}/contact-details")
    Map<String, String> updateContact(@PathVariable UUID patientId, @RequestBody @Valid UpdateContactRequest req) {
        var p = service.updatePatientContact(patientId, req.email(), req.phone());
        return Map.of("patientId", p.id().value().toString(), "email", p.email().value());
    }

    @PostMapping("/symptom-intakes")
    Map<String, String> submitIntake(@RequestBody @Valid SubmitIntakeRequest req) {
        var i = service.submitIntake(req.patientId(), req.symptoms());
        return Map.of("intakeId", i.id().value().toString());
    }

    @GetMapping("/symptom-intakes/{intakeId}")
    Map<String, String> getIntake(@PathVariable UUID intakeId) { return Map.of("intakeId", intakeId.toString()); }

    @PostMapping("/triage/evaluate")
    Map<String, String> evaluate(@RequestBody @Valid EvaluateTriageRequest req) {
        var t = service.evaluateTriage(req.patientId(), req.intakeId());
        return Map.of("triageCaseId", t.id().value().toString(), "outcome", t.outcome().getClass().getSimpleName());
    }

    @GetMapping("/triage/cases/{caseId}")
    Map<String, String> getTriageCase(@PathVariable UUID caseId) { return Map.of("triageCaseId", caseId.toString()); }

    @GetMapping("/appointments/slots")
    List<Map<String, String>> slots(@RequestParam Instant from, @RequestParam(defaultValue = "general") String specialty) {
        return service.searchSlots(from, specialty).stream().map(s -> Map.of("startsAt", s.startsAt().toString(), "specialty", s.specialty())).toList();
    }

    @PostMapping("/appointments")
    Map<String, String> book(@RequestBody @Valid BookAppointmentRequest req) {
        var appt = service.bookAppointment(req.patientId(), req.startsAt(), req.specialty(), req.urgency());
        return Map.of("appointmentId", appt.id().value().toString());
    }

    @DeleteMapping("/appointments/{appointmentId}")
    void cancel(@PathVariable UUID appointmentId) { service.cancelAppointment(appointmentId); }

    @PostMapping("/encounters")
    Map<String, String> encounter() { return Map.of("status", "encounter endpoint scaffolded"); }

    @GetMapping("/patients/{patientId}/timeline")
    List<Map<String, String>> timeline(@PathVariable UUID patientId) {
        return service.patientTimeline(patientId).stream().map(e -> Map.of("type", e.type(), "detail", e.detail(), "occurredAt", e.occurredAt().toString())).toList();
    }

    @PostMapping("/lab-orders")
    Map<String, String> createLab(@RequestBody @Valid CreateLabOrderRequest req) {
        var order = service.createLabOrder(req.patientId(), req.testName());
        return Map.of("labOrderId", order.id().value().toString());
    }

    @PostMapping("/lab-results")
    Map<String, String> attachLab(@RequestBody @Valid AttachLabResultRequest req) {
        var result = service.attachLabResult(req.labOrderId(), req.value());
        return Map.of("labOrderId", result.labOrderId().value().toString(), "status", result.status());
    }

    @GetMapping("/clinicians/{clinicianId}/dashboard")
    Map<String, Long> dashboard(@PathVariable UUID clinicianId) { return service.clinicianDashboard(); }

    @GetMapping("/audit-events")
    List<Map<String, String>> audit() {
        return service.auditTrail().stream().map(e -> Map.of("type", e.type(), "eventId", e.id().toString())).toList();
    }

    record RegisterPatientRequest(@NotBlank String givenName, @NotBlank String familyName, @Email String email, @NotBlank String phone, @NotNull LocalDate dateOfBirth) {}
    record UpdateContactRequest(@Email String email, @NotBlank String phone) {}
    record SubmitIntakeRequest(@NotNull UUID patientId, @NotEmpty List<String> symptoms) {}
    record EvaluateTriageRequest(@NotNull UUID patientId, @NotNull UUID intakeId) {}
    record BookAppointmentRequest(@NotNull UUID patientId, @NotNull Instant startsAt, @NotBlank String specialty, @NotNull TriageUrgency urgency) {}
    record CreateLabOrderRequest(@NotNull UUID patientId, @NotBlank String testName) {}
    record AttachLabResultRequest(@NotNull UUID labOrderId, @NotBlank String value) {}
}
