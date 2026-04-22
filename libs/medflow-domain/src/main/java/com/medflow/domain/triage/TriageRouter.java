package com.medflow.domain.triage;

public final class TriageRouter {
    public String routingMessage(TriageOutcome outcome) {
        return switch (outcome) {
            case EmergencyOutcome e -> "Route to emergency protocol: " + e.reason();
            case UrgentOutcome u -> "Book urgent appointment within " + u.maxWait();
            case RoutineOutcome r -> "Book routine appointment: " + r.reason();
            case SelfCareOutcome s -> "Provide self-care educational information: " + s.reason();
        };
    }
}
