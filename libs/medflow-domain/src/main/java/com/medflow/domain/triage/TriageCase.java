package com.medflow.domain.triage;

import com.medflow.domain.intake.IntakeId;
import com.medflow.domain.patient.PatientId;
import java.time.Instant;

public record TriageCase(TriageCaseId id, PatientId patientId, IntakeId intakeId, TriageOutcome outcome, Instant evaluatedAt) {}
