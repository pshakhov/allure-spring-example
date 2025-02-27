plugins {
    kotlin("jvm")
    `maven-publish`
    id("org.hidetake.swagger.generator") version "2.19.2"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(group = "com.squareup.moshi", name = "moshi-kotlin", "1.13.0")
    swaggerCodegen("io.swagger", "swagger-codegen-cli", "2.4.44")
}

swaggerSources {
    create("priestBackApiModel") {
        setInputFile(file("${project.projectDir}/src/main/resources/swagger/api-docs.json"))
        code.apply {
            language = "kotlin"
            components = listOf("models")
            additionalProperties = mapOf(
                "modelPackage" to "ru.tinkoff.opensource.model",
                "enumPropertyNaming" to "UPPERCASE"
            )

            outputDir = file("${project.buildDir}/generated/swagger")
        }

        sourceSets {
            main {
                java.srcDirs(code.outputDir.path + "/src/main/kotlin")
            }
        }

    }
}

tasks {
    compileJava {
        dependsOn(generateSwaggerCode)
    }

    compileKotlin {
        dependsOn(generateSwaggerCode)
    }
}