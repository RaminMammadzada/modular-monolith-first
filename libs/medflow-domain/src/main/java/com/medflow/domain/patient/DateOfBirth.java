package com.medflow.domain.patient;

import java.time.LocalDate;

public record DateOfBirth(LocalDate value) {
    public DateOfBirth {
        if (value == null || value.isAfter(LocalDate.now())) throw new IllegalArgumentException("invalid dob");
    }
}
