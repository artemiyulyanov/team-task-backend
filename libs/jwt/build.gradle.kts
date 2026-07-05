plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.teamtask.jwt"
version = "1.0.0"

kotlin {
    jvmToolchain(25)
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":libs:common-dto"))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}