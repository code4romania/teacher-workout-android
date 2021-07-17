import dependencies.Dependencies
import extensions.addProductFlavours

plugins {
    id("commons.android-library")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(Dependencies.AndroidX.Navigation.FRAGMENT)
    implementation(Dependencies.AndroidX.Navigation.UI)
}
