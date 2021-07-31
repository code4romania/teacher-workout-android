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
    implementation(Dependencies.AndroidX.RECYCLERVIEW)
    implementation(Dependencies.AndroidX.Navigation.UI)
    implementation(Dependencies.AndroidX.Navigation.DYNAMIC_FEATURE)

    implementation(Dependencies.AndroidX.Paging.RUNTIME)

    implementation(Dependencies.AndroidX.Compose.VIEWMODEL)
}
