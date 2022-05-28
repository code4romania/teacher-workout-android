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
    implementation(project(BuildModules.Features.DISCOVER))
    implementation(project(BuildModules.Features.PROFILE))
    implementation(project(BuildModules.Features.LESSON))
    implementation(project(BuildModules.Commons.UI))
}
