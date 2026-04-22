package com.medflow.domain.lab;

import java.util.UUID;

public record LabOrderId(UUID value) {
    public LabOrderId { if (value == null) throw new IllegalArgumentException("value required"); }
    public static LabOrderId newId() { return new LabOrderId(UUID.randomUUID()); }
}
