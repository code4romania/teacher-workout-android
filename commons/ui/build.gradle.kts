import extensions.addProductFlavours

plugins {
    id("commons.android-library")
}

android {
    addProductFlavours(this)
    namespace = "com.teacherworkout.commons.ui"
}
