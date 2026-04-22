package com.medflow.domain.appointment;

import com.medflow.domain.clinician.ClinicianId;
import java.time.Instant;

public record AppointmentSlot(ClinicianId clinicianId, Instant startsAt, Instant endsAt, String specialty) {}
