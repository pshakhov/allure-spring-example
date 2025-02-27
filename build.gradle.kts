plugins {
    kotlin("jvm") version "1.6.10"
    id("org.springframework.boot") version "2.5.4"
    kotlin("plugin.spring") version "1.6.10"
    id("io.qameta.allure") version "2.9.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"

val springVersion = "2.5.4" // FIXME up to 2.6.3

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":model"))

    implementation(kotlin("stdlib"))

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web-services", springVersion)
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-actuator", springVersion)
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-jpa", springVersion)
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-oauth2-client", springVersion)

    implementation(group = "org.springframework.cloud", name = "spring-cloud-starter-vault-config", "3.0.3")
    implementation(group = "org.springframework.cloud", name = "spring-cloud-starter-consul-config", "3.0.3")

    implementation("io.springfox:springfox-swagger2:2.10.5")
    implementation("io.springfox:springfox-swagger-ui:2.10.5")

    implementation("io.github.microutils:kotlin-logging:2.1.23")

    implementation(group = "org.postgresql", name = "postgresql", "+")
    implementation(group = "org.testcontainers", name = "postgresql", "1.16.0")
    implementation(group = "com.h2database", name = "h2", version = "1.4.200")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVersion")
}

tasks {
    test {
        useJUnitPlatform()
    }
}