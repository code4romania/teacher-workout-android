import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
    id("kotlin-android-extensions")
}

android {
    addProductFlavours(this)
}
dependencies {
    implementation(project(mapOf("path" to ":commons:ui")))
}
