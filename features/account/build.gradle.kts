import dependencies.Dependencies
import extensions.addProductFlavours

plugins {
    id("commons.android-library")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(project(":commons:ui"))
    implementation(Dependencies.AndroidX.Compose.PAGER)
}

// TODO needed to build the app because the compose pager is currently marked as experimental
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.freeCompilerArgs = listOf(
        "-Xopt-in=com.google.accompanist.pager.ExperimentalPagerApi"
    )
}
