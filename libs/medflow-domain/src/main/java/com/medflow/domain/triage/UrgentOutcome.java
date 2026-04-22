package com.medflow.domain.triage;

import java.time.Duration;
import java.util.Objects;

public record UrgentOutcome(String reason, Duration maxWait) implements TriageOutcome {
    public UrgentOutcome {
        Objects.requireNonNull(reason, "reason");
        Objects.requireNonNull(maxWait, "maxWait");
    }
}
