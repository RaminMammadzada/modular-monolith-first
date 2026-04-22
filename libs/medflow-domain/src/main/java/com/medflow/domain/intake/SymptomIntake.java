package com.medflow.domain.intake;

import com.medflow.domain.patient.PatientId;
import java.time.Instant;
import java.util.List;

public record SymptomIntake(IntakeId id, PatientId patientId, List<String> symptoms, Instant submittedAt) {
    public SymptomIntake {
        if (id == null || patientId == null || symptoms == null || submittedAt == null) throw new IllegalArgumentException("required");
        if (symptoms.isEmpty()) throw new IllegalArgumentException("symptoms required");
    }
}
