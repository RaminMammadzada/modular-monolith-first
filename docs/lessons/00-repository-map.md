# Lesson 00: Repository Bootstrap

## 1. Problem-Based Learning Scenario
The clinic project will grow quickly; we need reproducible structure and checkpoints.

## 2. Learning Goals
- Understand multi-module Gradle layout
- Understand Java toolchain pinning

## 3. Feynman Explanation
A toolchain pin says: everyone compiles with the same Java, no surprises.

Senior engineer view:
Deterministic build inputs reduce "works on my machine" drift and improve CI trust.

## 4. Socratic Questions
- Why is a pinned toolchain safer than local JDK defaults?

## 5. Hints
- Compare CI to local behavior.

## 6. Implementation Walkthrough
Bootstrap files, modules, and baseline docs were added.

## 7. Code Reading Guide
- settings.gradle.kts
- build.gradle.kts

## 8. Run It
```bash
./gradlew test
```

## 9. Tests To Inspect
- basic domain tests

## 10. JVM / Runtime Angle
JDK selection affects bytecode level and runtime behavior.

## 11. Reflection
Can you explain why reproducibility matters?

## 12. Exercises
- Add one more module.

## 13. Commit Map
- chore: initialize Java 25 Gradle multi-module project
