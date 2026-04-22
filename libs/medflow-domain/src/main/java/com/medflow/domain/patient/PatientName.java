package com.medflow.domain.patient;

import java.util.Objects;

public record PatientName(String given, String family) {
    public PatientName {
        Objects.requireNonNull(given);
        Objects.requireNonNull(family);
        if (given.isBlank() || family.isBlank()) throw new IllegalArgumentException("name parts required");
    }

    public String fullName() { return given + " " + family; }
}
