plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://plugins.gradle.org/m2/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

object PluginsVersions {
    const val GRADLE_ANDROID = "7.0.0"
    const val GRADLE_VERSIONS = "0.33.0"
    const val KOTLIN = "1.5.21"
    const val NAVIGATION = "2.3.0"
    const val APOLLO = "2.4.1"
    const val KOTLINPOET = "1.7.2"
    const val GPP = "3.5.0"
    const val KTLINT = "0.40.0"
    const val SPOTLESS = "5.7.0"
    const val DETEKT = "1.14.2"
    const val GRAPH_GENERATOR = "0.5.0"
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:${PluginsVersions.KOTLIN}")

    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")
}
