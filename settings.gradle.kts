@file:Suppress("UnstableApiUsage")

rootProject.name = "kotlin-jupyter-kernel"

pluginManagement {
    repositories {
        maven("https://packages.jetbrains.team/maven/p/kds/kotlin-ds-maven")
        gradlePluginPortal()
        if (System.getenv("KOTLIN_JUPYTER_USE_MAVEN_LOCAL") != null) {
            mavenLocal()
        }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version ("0.8.0")
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

includeBuild("build-plugin")

subproject("common-dependencies", "build-plugin/")
libSubproject("lib")
libSubproject("api")
libSubproject("api-annotations")
libSubproject("kotlin-jupyter-api-gradle-plugin")
libSubproject("shared-compiler")
libSubproject("spring-starter")
libSubproject("lib-ext")
libSubproject("test-kit")
libSubproject("test-kit-test")

exampleSubproject("getting-started")

fun libSubproject(name: String) = subproject(name, "jupyter-lib/")

fun exampleSubproject(name: String) = subproject(name, "api-examples/")

fun subproject(
    name: String,
    parentPath: String,
) {
    include(name)
    project(":$name").projectDir = file("$parentPath$name")
}
