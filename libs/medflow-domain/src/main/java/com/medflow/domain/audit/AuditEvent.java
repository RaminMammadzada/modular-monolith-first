package com.medflow.domain.audit;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record AuditEvent(UUID id, String type, UUID correlationId, Instant occurredAt, Map<String, String> metadata) {}
