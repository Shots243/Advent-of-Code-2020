import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jmailen.kotlinter") version "3.3.0"
    kotlin("jvm") version "1.4.21"
    application
}

group = "nwt.nergi"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

val mainLoc = "net.nergi.mainsource.Day25Kt"

val jar by tasks.getting(Jar::class) {
    manifest {
        attributes["Implementation-Version"] = archiveVersion
    }

    from ({
        configurations.runtimeClasspath.get().filter {
            it.name.endsWith("jar")
        }.map { zipTree(it) }
    })
}

application {
    mainClass.set(mainLoc)
}