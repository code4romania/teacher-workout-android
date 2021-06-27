import extensions.addProductFlavours
import dependencies.Dependencies

plugins {
    id("commons.android-dynamic-feature")
    id("kotlin-android")
}

android {
    addProductFlavours(this)
}
