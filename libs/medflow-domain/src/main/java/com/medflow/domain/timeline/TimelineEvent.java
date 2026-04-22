package com.medflow.domain.timeline;

import java.time.Instant;

public record TimelineEvent(String type, String detail, Instant occurredAt) {}
