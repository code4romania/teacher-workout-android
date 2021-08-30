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

import extensions.addTestDependencies
import dependencies.Dependencies

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("androidx.navigation.safeargs")
}

android {
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
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

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

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

    lint {
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
        kotlinCompilerVersion = Dependencies.Kotlin.VERSION
    }
}

dependencies {
    implementation(Dependencies.Coroutines.CORE)
    implementation(Dependencies.Coroutines.ANDROID)
    implementation(Dependencies.Kotlin.STDLIB)
    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINTLAYOUT)

    implementation(Dependencies.LOTTIE)

    implementation(Dependencies.COIL)

    implementation(Dependencies.AndroidX.Navigation.FRAGMENT)
    implementation(Dependencies.AndroidX.Navigation.UI)
    implementation(Dependencies.AndroidX.Navigation.DYNAMIC_FEATURE)

    implementation(Dependencies.Koin.ANDROID)
    implementation(Dependencies.AndroidX.Navigation.COMPOSE)
    implementation(Dependencies.MATERIAL)

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
    implementation(Dependencies.Koin.COMPOSE)

    addTestDependencies()
}
