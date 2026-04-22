#!/usr/bin/env bash
# Emits thread dump for a running PID.
set -euo pipefail
jcmd "$1" Thread.print
