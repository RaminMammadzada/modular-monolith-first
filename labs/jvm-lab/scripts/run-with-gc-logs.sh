#!/usr/bin/env bash
# Runs the JVM lab with GC logging enabled for educational inspection.
set -euo pipefail

script_dir="$(
  cd "$(dirname "${BASH_SOURCE[0]}")"
  pwd
)"
classes_dir="$(cd "$script_dir/.." && pwd)/build/classes/java/main"

java -Xlog:gc* -cp "$classes_dir" com.medflow.labs.jvm.JitWarmupDemo
