package com.medflow.domain.patient;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class PatientIdTest {
    @Test
    void createsValueObject() {
        var id = new PatientId(UUID.randomUUID());
        assertNotNull(id.value());
    }

    @Test
    void rejectsNull() {
        assertThrows(NullPointerException.class, () -> new PatientId(null));
    }
}
