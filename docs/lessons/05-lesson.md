# Lesson 05: Application Use Cases

## 1. Problem-Based Learning Scenario
The clinic receives intake forms and must orchestrate patient registration, triage, appointments, labs, and audit trails while keeping HTTP concerns outside core business logic.

## 2. Learning Goals
- Understand use-case orchestration in an application layer.
- Separate domain rules from transport and storage.
- Use explicit ports for persistence and side effects.

## 3. Feynman Explanation
Think of the application layer as an air-traffic controller: it does not fly planes itself, but coordinates all runway operations safely.

Senior engineer view:
Application services define transactional boundaries and order domain interactions while enforcing dependency inversion through ports.

## 4. Socratic Questions
- Why should a controller not talk directly to repositories?
- Which logic belongs in domain entities vs the use-case service?
- What failure mode appears if notifications are sent before state persistence?
- Why does explicit port design improve testability?
- How would you model retries for external adapters?

## 5. Hints
- Keep domain pure and deterministic.
- Treat adapters as plugins.

## 6. Implementation Walkthrough
`MedFlowService` now orchestrates patient registration, intake submission, triage evaluation, appointment booking/cancellation, lab ordering/results, timeline updates, and audit writes.

## 7. Code Reading Guide
1. `libs/medflow-application/.../ClinicPortBundle.java`
2. `libs/medflow-application/.../MedFlowService.java`
3. `apps/clinic-api/.../InMemoryClinicAdapters.java`
4. `apps/clinic-api/.../ClinicController.java`

## 8. Run It
```bash
./gradlew :medflow-application:test
./gradlew :clinic-api:bootRun
```

## 9. Tests To Inspect
- `MedFlowServiceTest`
- `ClinicControllerTest`

## 10. JVM / Runtime Angle
Most use-case logic is CPU-light orchestration. Blocking risk typically comes from adapter boundaries (database/network).

## 11. Reflection
Can you explain exactly where the transaction boundary should sit for booking + audit + notification?

## 12. Exercises
- Add idempotency key support for appointment booking.
- Add retry metadata for notifications.

## 13. Commit Map
- feat(application): add comprehensive MedFlow use-case orchestration
- feat(web): expose MedFlow endpoints with validation and problem details
- test(application): verify end-to-end service flow
