/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package commons

import BuildAndroidConfig
import BuildModules
import dependencies.Dependencies
import extensions.addTestDependencies

plugins {
    id("com.android.dynamic-feature")
    id("kotlin-android")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        // TODO get this from env properties file
        buildConfigField("String", "TOS_URL", "\"code4.ro\"")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    viewBinding {
        isEnabled = true
    }

    dataBinding {
        isEnabled = true
    }

    buildFeatures.compose = true

    sourceSets {
        getByName("main") {
            java.srcDir("src/main/kotlin")
        }
        getByName("test") {
            java.srcDir("src/test/kotlin")
        }
        getByName("androidTest") {
            java.srcDir("src/androidTest/kotlin")
        }
    }

    lintOptions {
        lintConfig = rootProject.file(".lint/config.xml")
        isCheckAllWarnings = true
        isWarningsAsErrors = true
    }

    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.0-rc02"
        kotlinCompilerVersion = "1.5.10"
    }
}

dependencies {
    implementation(project(BuildModules.APP))
    implementation(project(BuildModules.Commons.UI))

    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Coroutines.ANDROID)
    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.Navigation.FRAGMENT)
    implementation(Dependencies.AndroidX.Navigation.UI)
    implementation(Dependencies.AndroidX.Navigation.DYNAMIC_FEATURE)
    implementation(Dependencies.AndroidX.CONSTRAINTLAYOUT)

    implementation(Dependencies.AndroidX.Navigation.COMPOSE)

    implementation(Dependencies.AndroidX.Compose.FOUNDATION)
    implementation(Dependencies.AndroidX.Compose.UI)
    implementation(Dependencies.AndroidX.Compose.UI_TOOLING)
    implementation(Dependencies.AndroidX.Compose.MATERIAL)
    implementation(Dependencies.AndroidX.Compose.MATERIAL_ICONS)
    implementation(Dependencies.AndroidX.Compose.MATERIAL_ICONS_EXTENDED)
    implementation(Dependencies.AndroidX.Compose.ACTIVITY)
    implementation(Dependencies.AndroidX.Compose.VIEWMODEL)
    implementation(Dependencies.AndroidX.Compose.LIVEDATA)

    implementation(Dependencies.Koin.CORE)
    implementation(Dependencies.Koin.ANDROID)
    implementation(Dependencies.Koin.COMPOSE)

    addTestDependencies()
}
