#!/usr/bin/env bash
# Starts a short Java Flight Recorder capture for the JVM lab.
set -euo pipefail
java -XX:StartFlightRecording=filename=medflow.jfr,duration=20s -cp ../../build/classes/java/main com.medflow.labs.jvm.JitWarmupDemo
