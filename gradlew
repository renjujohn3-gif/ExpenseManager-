#!/bin/sh
# GitHub/mobile-friendly Gradle launcher.
# GitHub Actions installs the requested Gradle version before invoking this script.
set -e
if command -v gradle >/dev/null 2>&1; then
  exec gradle "$@"
fi
echo "ERROR: Gradle is not installed or is not on PATH." >&2
exit 1
