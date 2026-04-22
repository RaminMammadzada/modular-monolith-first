package com.medflow.domain.triage;

import java.util.Objects;

public record RoutineOutcome(String reason) implements TriageOutcome {
    public RoutineOutcome {
        Objects.requireNonNull(reason, "reason");
    }
}
