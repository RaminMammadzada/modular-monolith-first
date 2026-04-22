package com.medflow.domain.appointment;

import com.medflow.domain.patient.PatientId;
import com.medflow.domain.triage.TriageUrgency;
import java.time.Instant;

public record Appointment(AppointmentId id, PatientId patientId, AppointmentSlot slot, TriageUrgency urgency, Instant bookedAt, boolean cancelled) {
    public Appointment cancel() { return new Appointment(id, patientId, slot, urgency, bookedAt, true); }
}
