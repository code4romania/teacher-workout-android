plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

repositories {
    google()
    jcenter()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
}

object PluginsVersions {
    const val KOTLIN = "1.5.20"
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${PluginsVersions.KOTLIN}")
    implementation("androidx.core:core-ktx:1.5.0")
    implementation("androidx.appcompat:appcompat:1.3.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.+")
    testImplementation("androidx.test.ext:junit:1.1.2")
    testImplementation("androidx.test.espresso:espresso-core:3.3.0")
}