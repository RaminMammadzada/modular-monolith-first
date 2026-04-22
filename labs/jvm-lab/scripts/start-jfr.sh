#!/usr/bin/env bash
# Starts a short Java Flight Recorder capture for the JVM lab.
set -euo pipefail

script_dir="$(
  cd "$(dirname "${BASH_SOURCE[0]}")"
  pwd
)"
classes_dir="$(cd "$script_dir/.." && pwd)/build/classes/java/main"

java -XX:StartFlightRecording=filename=medflow.jfr,duration=20s -cp "$classes_dir" com.medflow.labs.jvm.JitWarmupDemo
