import dependencies.Dependencies
import extensions.addProductFlavours

plugins {
    id(BuildPlugins.ANDROID_APPLICATION)
    id(BuildPlugins.KOTLIN_ANDROID)
}

android {
    compileSdkVersion(BuildAndroidConfig.COMPILE_SDK_VERSION)

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdkVersion(BuildAndroidConfig.MIN_SDK_VERSION)
        targetSdkVersion(BuildAndroidConfig.TARGET_SDK_VERSION)
        buildToolsVersion(BuildAndroidConfig.BUILD_TOOLS_VERSION)

        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME

        testInstrumentationRunner = BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER
        testInstrumentationRunnerArguments.putAll(BuildAndroidConfig.TEST_INSTRUMENTATION_RUNNER_ARGUMENTS)
    }

    buildTypes {
        getByName(BuildType.RELEASE) {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
        }
        getByName(BuildType.DEBUG) {
            signingConfig = signingConfigs.getByName(BuildType.DEBUG)
            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
        }
    }

    addProductFlavours(this)

    dynamicFeatures = mutableSetOf(
        BuildModules.Features.ACCOUNT,
        BuildModules.Features.HOME,
        BuildModules.Features.LEARN,
        BuildModules.Features.PROFILE
    )

    buildFeatures {
        dataBinding = true
        viewBinding = true
        compose = true
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    composeOptions {
        kotlinCompilerVersion = Dependencies.Kotlin.VERSION
        kotlinCompilerExtensionVersion = "1.0.0-rc02"
    }
}

dependencies {
    implementation(project(BuildModules.CORE))
    implementation(project(BuildModules.Commons.UI))
    implementation(project(BuildModules.Commons.VIEWS))

    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINTLAYOUT)
    implementation(Dependencies.AndroidX.Lifecycle.VIEWMODEL)
    implementation(Dependencies.AndroidX.Lifecycle.EXTENSIONS)

    implementation(Dependencies.MATERIAL)

    implementation(Dependencies.AndroidX.Navigation.FRAGMENT)
    implementation(Dependencies.AndroidX.Navigation.UI)
    implementation(Dependencies.AndroidX.Navigation.DYNAMIC_FEATURE)
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
}
