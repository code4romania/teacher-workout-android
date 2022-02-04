import extensions.addProductFlavours
import dependencies.Dependencies

plugins {
    id("commons.android-library")
    id(BuildPlugins.Apollo3.ID).version(BuildPlugins.Apollo3.VERSION)
}

apollo {
    packageName.set("com.teacherworkout.core")
    codegenModels.set("responseBased")
}

android {
    addProductFlavours(this)
}

dependencies {
    implementation(Dependencies.Apollo3.RUNTIME)
}
