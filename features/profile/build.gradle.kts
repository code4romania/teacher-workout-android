import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
    namespace = "com.teacherworkout.features.profile"
}

dependencies {
    implementation(project(BuildModules.Commons.UI))
}
