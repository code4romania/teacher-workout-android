import extensions.addProductFlavours
import dependencies.Dependencies

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
    implementation(Dependencies.COIL_COMPOSE)
}
