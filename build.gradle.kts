plugins {
    java
    kotlin("jvm") version "1.5.31"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    testImplementation("junit", "junit", "4.12")
}

application {
    this.applicationName = "ideolog"
    mainClassName = "com.flaax.ideolog.MainKt"
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}