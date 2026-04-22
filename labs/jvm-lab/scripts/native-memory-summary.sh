#!/usr/bin/env bash
# Prints Native Memory Tracking summary for a running PID.
set -euo pipefail
jcmd "$1" VM.native_memory summary
