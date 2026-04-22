package com.medflow.domain.encounter;

import java.util.UUID;

public record EncounterId(UUID value) {
    public EncounterId { if (value == null) throw new IllegalArgumentException("value required"); }
    public static EncounterId newId() { return new EncounterId(UUID.randomUUID()); }
}
