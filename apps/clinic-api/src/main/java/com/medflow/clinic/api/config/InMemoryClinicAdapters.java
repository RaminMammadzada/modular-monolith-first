package com.medflow.clinic.api.config;

import com.medflow.application.ports.ClinicPortBundle;
import com.medflow.domain.appointment.*;
import com.medflow.domain.audit.AuditEvent;
import com.medflow.domain.clinician.ClinicianId;
import com.medflow.domain.intake.*;
import com.medflow.domain.lab.*;
import com.medflow.domain.patient.*;
import com.medflow.domain.timeline.TimelineEvent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryClinicAdapters {
    @Bean ClinicPortBundle.Patients patients() {
        Map<UUID, Patient> store = new ConcurrentHashMap<>();
        return new ClinicPortBundle.Patients() {
            public void save(Patient p) { store.put(p.id().value(), p); }
            public Optional<Patient> findById(PatientId id) { return Optional.ofNullable(store.get(id.value())); }
        };
    }

    @Bean ClinicPortBundle.Intakes intakes() {
        Map<UUID, SymptomIntake> store = new ConcurrentHashMap<>();
        return new ClinicPortBundle.Intakes() {
            public void save(SymptomIntake i) { store.put(i.id().value(), i); }
            public Optional<SymptomIntake> findById(IntakeId id) { return Optional.ofNullable(store.get(id.value())); }
        };
    }

    @Bean ClinicPortBundle.Triages triages() {
        Map<UUID, com.medflow.domain.triage.TriageCase> store = new ConcurrentHashMap<>();
        return new ClinicPortBundle.Triages() {
            public void save(com.medflow.domain.triage.TriageCase c) { store.put(c.id().value(), c); }
            public Optional<com.medflow.domain.triage.TriageCase> findById(com.medflow.domain.triage.TriageCaseId id) { return Optional.ofNullable(store.get(id.value())); }
        };
    }

    @Bean ClinicPortBundle.Appointments appointments() {
        Map<UUID, Appointment> store = new ConcurrentHashMap<>();
        return new ClinicPortBundle.Appointments() {
            public void save(Appointment a) { store.put(a.id().value(), a); }
            public Optional<Appointment> findById(AppointmentId id) { return Optional.ofNullable(store.get(id.value())); }
            public List<AppointmentSlot> findSlots(Instant from, String specialty) {
                var c = new ClinicianId(UUID.fromString("00000000-0000-0000-0000-000000000111"));
                return List.of(
                        new AppointmentSlot(c, from.plus(2, ChronoUnit.HOURS), from.plus(3, ChronoUnit.HOURS), specialty),
                        new AppointmentSlot(c, from.plus(1, ChronoUnit.DAYS), from.plus(1, ChronoUnit.DAYS).plus(1, ChronoUnit.HOURS), specialty));
            }
        };
    }

    @Bean ClinicPortBundle.Labs labs() {
        Map<UUID, LabOrder> orders = new ConcurrentHashMap<>();
        Map<UUID, LabResult> results = new ConcurrentHashMap<>();
        return new ClinicPortBundle.Labs() {
            public void saveOrder(LabOrder o) { orders.put(o.id().value(), o); }
            public Optional<LabOrder> findOrder(LabOrderId id) { return Optional.ofNullable(orders.get(id.value())); }
            public void saveResult(LabResult r) { results.put(r.labOrderId().value(), r); }
            public Optional<LabResult> findResult(LabOrderId id) { return Optional.ofNullable(results.get(id.value())); }
        };
    }

    @Bean ClinicPortBundle.Notifications notifications() {
        return (patientId, message) -> { };
    }

    @Bean ClinicPortBundle.Audit audit() {
        List<AuditEvent> events = new CopyOnWriteArrayList<>();
        return new ClinicPortBundle.Audit() {
            public void write(AuditEvent event) { events.add(event); }
            public List<AuditEvent> list() { return List.copyOf(events); }
        };
    }

    @Bean ClinicPortBundle.Timeline timeline() {
        Map<UUID, List<TimelineEvent>> events = new ConcurrentHashMap<>();
        return new ClinicPortBundle.Timeline() {
            public void append(PatientId id, TimelineEvent event) { events.computeIfAbsent(id.value(), _ -> new CopyOnWriteArrayList<>()).add(event); }
            public List<TimelineEvent> forPatient(PatientId id) { return List.copyOf(events.getOrDefault(id.value(), List.of())); }
        };
    }
}
