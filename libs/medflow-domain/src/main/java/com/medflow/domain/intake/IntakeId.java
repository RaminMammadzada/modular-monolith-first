package com.medflow.domain.intake;

import java.util.UUID;

public record IntakeId(UUID value) {
    public IntakeId { if (value == null) throw new IllegalArgumentException("value required"); }
    public static IntakeId newId() { return new IntakeId(UUID.randomUUID()); }
}
