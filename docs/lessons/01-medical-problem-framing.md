# Lesson 01: Medical Problem Framing

## 1. Problem-Based Learning Scenario
Intake must route safely, but this project must never claim clinical authority.

## 2. Learning Goals
- Bound domain scope
- Add safety constraints

## 3. Feynman Explanation
We model workflows, not diagnoses.

Senior engineer view:
Safety disclaimers and bounded contexts prevent accidental misuse and misrepresentation.

## 4. Socratic Questions
- What legal/ethical risk appears if educational triage is presented as medical advice?

## 5. Hints
- Separate simulation from real-world claims.

## 6. Implementation Walkthrough
Safety and architecture docs were added.

## 7. Code Reading Guide
- MEDICAL_SAFETY_NOTE.md
- docs/architecture/context-map.md

## 8. Run It
```bash
./gradlew test
```

## 9. Tests To Inspect
- Domain construction tests

## 10. JVM / Runtime Angle
N/A

## 11. Reflection
Can you defend why this is educational-only software?

## 12. Exercises
- Add one additional bounded context.

## 13. Commit Map
- docs(domain): define MedFlow problem and bounded contexts
