import dependencies.Dependencies
import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id("kotlin-android")
    id(BuildPlugins.APOLLO).version("3.8.2")
}

android {
    addProductFlavours(this)
    namespace = "com.teacherworkout.features.discover"

    apollo {
        service("service") {
            packageName.set("com.teacherworkout.discover.dtos")
        }
    }

}

dependencies {
    implementation(project(mapOf("path" to ":commons:ui")))
    implementation(project(BuildModules.CORE))

    implementation(Dependencies.Apollo.RUNTIME)
    apolloMetadata(project(BuildModules.CORE))
}
