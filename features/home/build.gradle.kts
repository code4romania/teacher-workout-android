import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
    namespace = "com.teacherworkout.features.home"
}
dependencies {
    implementation(project(BuildModules.Features.DISCOVER))
    implementation(project(BuildModules.Features.PROFILE))
    implementation(project(BuildModules.Features.LESSON))
    implementation(project(BuildModules.Commons.UI))
}
