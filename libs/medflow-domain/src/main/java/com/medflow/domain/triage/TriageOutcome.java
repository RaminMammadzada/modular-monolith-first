package com.medflow.domain.triage;

// EDUCATIONAL SAFETY WARNING:
// This triage logic is simplified for software engineering education.
// It must not be used for real clinical decisions.
public sealed interface TriageOutcome permits EmergencyOutcome, UrgentOutcome, RoutineOutcome, SelfCareOutcome {
    String reason();
}
