// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.5.10")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:4.2.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
