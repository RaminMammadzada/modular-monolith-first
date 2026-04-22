package com.medflow.domain.triage;

import java.util.UUID;

public record TriageCaseId(UUID value) {
    public TriageCaseId { if (value == null) throw new IllegalArgumentException("value required"); }
    public static TriageCaseId newId() { return new TriageCaseId(UUID.randomUUID()); }
}
