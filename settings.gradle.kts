rootProject.name = "team-task-backend"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
    }
}

include(
    "libs:common-dto",
    "libs:jwt",
//    "libs:kafka-events",
//    "libs:observability",

    "services:api-gateway",
//    "services:auth-service",
//    "services:task-service",
//    "services:project-service",
//    "services:calendar-service",
//    "services:meeting-service",
//    "services:notification-service",
//    "services:user-service",
)