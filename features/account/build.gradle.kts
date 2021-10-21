import extensions.addProductFlavours

plugins {
    id("commons.android-library")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(project(":commons:ui"))
}
