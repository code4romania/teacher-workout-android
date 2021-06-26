package dependencies

object Dependencies {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val MATERIAL = "com.google.android.material:material:1.3.0"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val LOTTIE = "com.airbnb.android:lottie:3.6.1"
    const val COIL = "io.coil-kt:coil:1.1.1"

    object Coroutines {
        const val VERSION = "1.4.3"

        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object Kotlin {
        private const val VERSION = "1.4.10"

        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$VERSION"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.3.2"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.2.0"
        const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val FRAGMENT = "androidx.fragment:fragment-ktx:1.2.5"
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.1.0"

        object Navigation {
            private const val VERSION = "2.3.4"

            const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"
            const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
            const val DYNAMIC_FEATURE =
                "androidx.navigation:navigation-dynamic-features-fragment:$VERSION"
        }

        object Lifecycle {
            private const val VERSION = "2.2.0"

            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0"
            const val EXTENSIONS = "androidx.lifecycle:lifecycle-extensions:$VERSION"
        }

        object Paging {
            private const val VERSION = "2.1.2"

            const val RUNTIME = "androidx.paging:paging-runtime-ktx:$VERSION"
        }
    }

    object Koin {
        const val VERSION = "2.2.2"

        const val ANDROID = "org.koin:koin-android:$VERSION"
        const val ANDROID_VIEWMODEL = "org.koin:koin-android-viewmodel:$VERSION"
    }

    object Apollo {
        const val VERSION = "2.5.4"

        const val RUNTIME = "com.apollographql.apollo:apollo-runtime:$VERSION"
        const val COROUTINES_SUPPORT = "com.apollographql.apollo:apollo-coroutines-support:$VERSION"
    }
}
