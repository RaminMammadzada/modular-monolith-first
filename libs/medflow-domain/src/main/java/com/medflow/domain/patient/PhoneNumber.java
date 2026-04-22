package com.medflow.domain.patient;

public record PhoneNumber(String value) {
    public PhoneNumber {
        if (value == null || !value.matches("\\+?[0-9\\- ]{7,20}")) throw new IllegalArgumentException("invalid phone");
    }
}
