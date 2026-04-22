package com.medflow.domain.triage;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import org.junit.jupiter.api.Test;

class TriageRouterTest {
    private final TriageRouter router = new TriageRouter();

    @Test
    void routesUrgentCase() {
        String msg = router.routingMessage(new UrgentOutcome("tachycardia symptoms", Duration.ofHours(2)));
        assertTrue(msg.contains("urgent"));
    }
}
