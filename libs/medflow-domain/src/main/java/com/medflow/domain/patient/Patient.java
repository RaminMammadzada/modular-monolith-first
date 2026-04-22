package com.medflow.domain.patient;

import java.util.Objects;

public record Patient(PatientId id, PatientName name, EmailAddress email, PhoneNumber phone, DateOfBirth dob) {
    public Patient {
        Objects.requireNonNull(id); Objects.requireNonNull(name); Objects.requireNonNull(email);
        Objects.requireNonNull(phone); Objects.requireNonNull(dob);
    }

    public Patient updateContact(EmailAddress newEmail, PhoneNumber newPhone) {
        return new Patient(id, name, newEmail, newPhone, dob);
    }
}
