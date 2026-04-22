package com.medflow.domain.clinician;

import java.util.UUID;

public record ClinicianId(UUID value) {
    public ClinicianId {
        if (value == null) throw new IllegalArgumentException("value required");
    }
}
