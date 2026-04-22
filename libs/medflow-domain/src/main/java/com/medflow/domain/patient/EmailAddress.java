package com.medflow.domain.patient;

import java.util.Objects;

public record EmailAddress(String value) {
    public EmailAddress {
        Objects.requireNonNull(value, "value");
        if (!value.contains("@")) {
            throw new IllegalArgumentException("Email must contain @");
        }
    }
}
