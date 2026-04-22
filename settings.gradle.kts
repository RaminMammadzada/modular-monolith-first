plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.9.0"
}

rootProject.name = "medflow-java25-lab"

include(
    "clinic-api",
    "medflow-domain",
    "medflow-application",
    "medflow-adapters-web",
    "medflow-adapters-persistence",
    "medflow-adapters-notification",
    "medflow-observability",
    "medflow-testing",
    "triage-cli",
    "java25-language-lab",
    "jvm-lab",
    "structured-concurrency-preview-lab"
)

project(":clinic-api").projectDir = file("apps/clinic-api")
project(":medflow-domain").projectDir = file("libs/medflow-domain")
project(":medflow-application").projectDir = file("libs/medflow-application")
project(":medflow-adapters-web").projectDir = file("libs/medflow-adapters-web")
project(":medflow-adapters-persistence").projectDir = file("libs/medflow-adapters-persistence")
project(":medflow-adapters-notification").projectDir = file("libs/medflow-adapters-notification")
project(":medflow-observability").projectDir = file("libs/medflow-observability")
project(":medflow-testing").projectDir = file("libs/medflow-testing")
project(":triage-cli").projectDir = file("tools/triage-cli")
project(":java25-language-lab").projectDir = file("labs/java25-language-lab")
project(":jvm-lab").projectDir = file("labs/jvm-lab")
project(":structured-concurrency-preview-lab").projectDir = file("labs/structured-concurrency-preview-lab")
