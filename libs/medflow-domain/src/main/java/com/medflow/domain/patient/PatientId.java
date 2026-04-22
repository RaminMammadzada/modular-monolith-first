package com.medflow.domain.patient;

import java.util.Objects;
import java.util.UUID;

// LEARN:
// A record is ideal here because PatientId is immutable identity by value.
public record PatientId(UUID value) {
    public PatientId {
        Objects.requireNonNull(value, "value");
    }

    public static PatientId newId() {
        return new PatientId(UUID.randomUUID());
    }
}
