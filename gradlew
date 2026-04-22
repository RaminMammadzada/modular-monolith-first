#!/usr/bin/env bash
set -euo pipefail
if ! command -v gradle >/dev/null 2>&1; then
  echo "Gradle is required on PATH because this repository omits binary wrapper JAR for PR-system compatibility." >&2
  exit 1
fi
exec gradle "$@"
