plugins {
    kotlin("jvm")
    kotlin("plugin.jpa") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(group = "org.springframework.boot", name = "spring-boot-starter-data-jpa", "2.5.4")
    implementation(group = "org.liquibase", name= "liquibase-core", version= "4.2.2")
}