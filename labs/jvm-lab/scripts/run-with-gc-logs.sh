#!/usr/bin/env bash
# Runs the JVM lab with GC logging enabled for educational inspection.
set -euo pipefail
java -Xlog:gc* -cp ../../build/classes/java/main com.medflow.labs.jvm.JitWarmupDemo
