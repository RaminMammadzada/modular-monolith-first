package com.medflow.domain.appointment;

import java.util.UUID;

public record AppointmentId(UUID value) {
    public AppointmentId { if (value == null) throw new IllegalArgumentException("value required"); }
    public static AppointmentId newId() { return new AppointmentId(UUID.randomUUID()); }
}
