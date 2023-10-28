import dependencies.Dependencies
import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id(BuildPlugins.APOLLO).version("3.8.2")
}

android {
    addProductFlavours(this)
    namespace = "com.teacherworkout.core"

    apollo {
        service("service") {
            packageName.set("com.teacherworkout.dtos")
            generateApolloMetadata.set(true)
        }
    }
}

dependencies {
    implementation(Dependencies.Apollo.RUNTIME)
}

