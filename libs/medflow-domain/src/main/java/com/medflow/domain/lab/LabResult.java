package com.medflow.domain.lab;

import java.time.Instant;

public record LabResult(LabOrderId labOrderId, String value, String status, Instant attachedAt) {}
