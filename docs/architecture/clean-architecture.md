# Clean / Hexagonal Architecture

- Domain: pure Java types, no Spring/JPA annotations.
- Application: use cases and ports.
- Adapters: web/persistence/notification implementations.
- Infrastructure: Spring Boot wiring, docker, CI, runtime concerns.

This repository keeps orchestration in `MedFlowService` and isolates IO details behind ports.
