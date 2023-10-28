import extensions.addProductFlavours
import dependencies.Dependencies

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
    namespace = "com.teacherworkout.features.lesson"
}
dependencies {
    implementation(project(mapOf("path" to ":commons:ui")))
    implementation(Dependencies.AndroidX.Compose.CONSTRAINT_LAYOUT)
}
