plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "com.teamtask.jwt"
version = "1.0.0"

kotlin {
    jvmToolchain(25)
}

dependencies {
    // подключаем BOM, чтобы версии Spring/Reactor резолвились без явного указания
    compileOnly(platform("org.springframework.boot:spring-boot-dependencies:4.0.0"))
    testImplementation(platform("org.springframework.boot:spring-boot-dependencies:4.0.0"))

    compileOnly(libs.spring.boot.starter)
    compileOnly(libs.spring.boot.starter.webflux)
    compileOnly(libs.reactor.core)

    implementation(libs.jjwt.api)
    runtimeOnly(libs.jjwt.impl)
    runtimeOnly(libs.jjwt.jackson)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.reactor.test)
}

tasks.test {
    useJUnitPlatform()
}