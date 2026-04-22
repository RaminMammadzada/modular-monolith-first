package com.medflow.domain.encounter;

import com.medflow.domain.appointment.AppointmentId;
import com.medflow.domain.clinician.ClinicianId;
import java.time.Instant;

public record Encounter(EncounterId id, AppointmentId appointmentId, ClinicianId clinicianId, String note, Instant recordedAt) {}
