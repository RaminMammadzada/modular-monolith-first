package com.medflow.domain.context;

// LEARN:
// ScopedValue gives immutable, bounded-lifetime context propagation.
public final class RequestContextHolder {
    public static final ScopedValue<RequestContext> CURRENT = ScopedValue.newInstance();

    private RequestContextHolder() {
    }
}
