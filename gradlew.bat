@echo off
where gradle >nul 2>nul
if errorlevel 1 (
  echo Gradle is required on PATH because this repository omits binary wrapper JAR for PR-system compatibility.
  exit /b 1
)
gradle %*
