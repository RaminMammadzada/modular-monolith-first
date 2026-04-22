package com.medflow.domain.notification;

import java.time.Instant;
import java.util.UUID;

public record Notification(UUID id, UUID patientId, String channel, String message, Instant createdAt) {}
