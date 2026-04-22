package com.medflow.domain.triage;

import java.util.Objects;

public record SelfCareOutcome(String reason) implements TriageOutcome {
    public SelfCareOutcome {
        Objects.requireNonNull(reason, "reason");
    }
}
