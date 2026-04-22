package com.medflow.domain.context;

import java.time.Instant;
import java.util.UUID;

public record RequestContext(UUID correlationId, Instant startedAt, String actor) {}
