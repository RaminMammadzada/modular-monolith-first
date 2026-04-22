# medflow-java25-lab

A production-style educational Java 25 repository for a synthetic clinic platform named **MedFlow**.

## Safety first
This project is **software-engineering education only**. It is **not medical advice**, **not a medical device**, and **not clinical decision support**. All data and triage rules are synthetic and simplified.

## What this teaches
- Java 25 language and modern style
- Records, sealed interfaces, pattern matching switch
- Scoped values and virtual-thread-ready architecture
- Hexagonal architecture and use-case orchestration
- REST API + validation + Problem Details
- JVM observability and diagnostics labs


## Gradle note
This repository omits `gradle-wrapper.jar` to satisfy PR systems that reject binary diffs.
`./gradlew` is a shim to local `gradle` on PATH.

## Quick start
```bash
./gradlew clean test
./gradlew :clinic-api:bootRun
```

## Lesson-driven workflow
```bash
git switch lesson/00-repository-bootstrap
./gradlew test
read docs/lessons/00-repository-map.md

git switch lesson/03-sealed-triage-model
./gradlew test
read docs/lessons/03-lesson.md
```

## Core endpoint samples
```bash
curl -X POST localhost:8080/api/patients \
  -H 'content-type: application/json' \
  -d '{"givenName":"Ada","familyName":"Patient","email":"ada@example.test","phone":"+12025550101","dateOfBirth":"1990-01-01"}'
```

## JVM labs
```bash
./gradlew :jvm-lab:test
labs/jvm-lab/scripts/start-jfr.sh
labs/jvm-lab/scripts/run-with-gc-logs.sh
```

## Git history exploration
```bash
git log --oneline --graph --decorate --all
git diff lesson-02..lesson-03
```
