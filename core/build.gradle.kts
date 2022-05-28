import dependencies.Dependencies
import extensions.addProductFlavours

plugins {
    id("commons.android-library")
    id(BuildPlugins.APOLLO).version("3.0.0")
}

android {
    addProductFlavours(this)

    apollo {
        packageName.set("com.teacherworkout.dtos")
        generateApolloMetadata.set(true)
    }
}

dependencies {
    implementation(Dependencies.Apollo.RUNTIME)
}

