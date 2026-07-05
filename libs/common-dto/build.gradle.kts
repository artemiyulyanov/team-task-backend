plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.lombok)
}

group = "com.teamtask.dto"
version = "1.0.0"

kotlin {
    jvmToolchain(25)
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation(libs.jakarta.validation)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}