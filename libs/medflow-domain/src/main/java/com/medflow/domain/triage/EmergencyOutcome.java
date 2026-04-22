package com.medflow.domain.triage;

import java.util.Objects;

public record EmergencyOutcome(String reason) implements TriageOutcome {
    public EmergencyOutcome {
        Objects.requireNonNull(reason, "reason");
        if (reason.isBlank()) throw new IllegalArgumentException("reason is required");
    }
}
