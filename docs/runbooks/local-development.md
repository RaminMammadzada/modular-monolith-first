# Local Development

```bash
./gradlew clean test
./gradlew :clinic-api:bootRun
```

`docker compose up -d` is optional in the current scaffold. The checked-in API uses in-memory adapters; Postgres is present for later persistence lessons.

Use synthetic identities only (Ada Patient, Bruno Patient, Clara Clinician).
