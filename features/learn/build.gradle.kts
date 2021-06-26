import extensions.addProductFlavours
import dependencies.Dependencies

plugins {
    id("commons.android-dynamic-feature")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}
dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
}
