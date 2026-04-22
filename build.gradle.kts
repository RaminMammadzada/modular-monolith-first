plugins {
    id("org.springframework.boot") version "3.5.0" apply false
    id("io.spring.dependency-management") version "1.1.7" apply false
    java
}

allprojects {
    group = "com.medflow"
    version = "0.1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "java")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(25))
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    dependencies {
        testImplementation(platform("org.junit:junit-bom:5.13.0"))
        testImplementation("org.junit.jupiter:junit-jupiter")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

project(":clinic-api") {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation(project(":medflow-domain"))
        implementation(project(":medflow-application"))
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}

project(":medflow-application") {
    dependencies {
        implementation(project(":medflow-domain"))
        testImplementation("com.tngtech.archunit:archunit-junit5:1.4.1")
    }
}

project(":medflow-adapters-persistence") {
    apply(plugin = "io.spring.dependency-management")
    dependencies {
        implementation(project(":medflow-application"))
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.flywaydb:flyway-core")
        runtimeOnly("org.postgresql:postgresql")
        testImplementation("org.testcontainers:junit-jupiter:1.20.4")
        testImplementation("org.testcontainers:postgresql:1.20.4")
    }
}
