plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.teamtask.dto"
version = "1.0.0"

kotlin {
    jvmToolchain(25)
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}