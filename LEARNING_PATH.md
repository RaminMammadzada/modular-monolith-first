# Learning Path

| Lesson | Branch | Tag | Main concept | Java/JVM topic | What to run |
|--------|--------|-----|--------------|----------------|-------------|
| 00 | lesson/00-repository-bootstrap | lesson-00 | repository bootstrap | toolchain + modules | `./gradlew test` |
| 01 | lesson/01-medical-problem-framing | lesson-01 | medical boundaries | safety modeling | `./gradlew test` |
| 02 | lesson/02-domain-records-value-objects | lesson-02 | records | immutability + invariants | `./gradlew :medflow-domain:test` |
| 03 | lesson/03-sealed-triage-model | lesson-03 | sealed triage outcomes | closed domain modeling | `./gradlew :medflow-domain:test` |
| 04 | lesson/04-pattern-matching-routing | lesson-04 | switch pattern matching | exhaustiveness | `./gradlew :medflow-domain:test` |
| 05 | lesson/05-application-use-cases | lesson-05 | use cases + ports | clean architecture | `./gradlew :medflow-application:test` |
| 06-28 | lesson/... | lesson-06..28 | progressively deeper | persistence, API, security, observability, JVM | `./gradlew clean test` |
