package com.medflow.application.ports;

import com.medflow.domain.appointment.*;
import com.medflow.domain.audit.AuditEvent;
import com.medflow.domain.intake.*;
import com.medflow.domain.lab.*;
import com.medflow.domain.patient.*;
import com.medflow.domain.timeline.TimelineEvent;
import com.medflow.domain.triage.*;
import java.time.Instant;
import java.util.*;

public interface ClinicPortBundle {
    interface Patients {
        void save(Patient patient);
        Optional<Patient> findById(PatientId id);
    }
    interface Intakes {
        void save(SymptomIntake intake);
        Optional<SymptomIntake> findById(IntakeId id);
    }
    interface Triages {
        void save(TriageCase triageCase);
        Optional<TriageCase> findById(TriageCaseId id);
    }
    interface Appointments {
        void save(Appointment appointment);
        Optional<Appointment> findById(AppointmentId id);
        List<AppointmentSlot> findSlots(Instant from, String specialty);
    }
    interface Labs {
        void saveOrder(LabOrder order);
        Optional<LabOrder> findOrder(LabOrderId id);
        void saveResult(LabResult result);
        Optional<LabResult> findResult(LabOrderId id);
    }
    interface Notifications { void send(UUID patientId, String message); }
    interface Audit { void write(AuditEvent event); List<AuditEvent> list(); }
    interface Timeline { void append(PatientId id, TimelineEvent event); List<TimelineEvent> forPatient(PatientId id); }
}
