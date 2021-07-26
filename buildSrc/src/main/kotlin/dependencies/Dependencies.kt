package dependencies

object Dependencies {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val MATERIAL = "com.google.android.material:material:1.4.0"
    const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"
    const val LOTTIE = "com.airbnb.android:lottie:3.6.1"
    const val COIL = "io.coil-kt:coil:1.1.1"

    object Coroutines {
        const val VERSION = "1.5.1"

        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object Kotlin {
        /**
         * Note: Be careful when changing this because different versions of the compose compiler require different
         * specific versions of Kotlin to work!
         */
        const val VERSION = "1.5.10"

        const val STDLIB = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$VERSION"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.6.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.3.0"
        const val CONSTRAINTLAYOUT = "androidx.constraintlayout:constraintlayout:2.0.4"
        const val FRAGMENT = "androidx.fragment:fragment-ktx:1.4.0-alpha04"
        const val RECYCLERVIEW = "androidx.recyclerview:recyclerview:1.1.0"

        object Navigation {
            private const val VERSION = "2.4.0-alpha04"

            const val FRAGMENT = "androidx.navigation:navigation-fragment-ktx:$VERSION"
            const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
            const val DYNAMIC_FEATURE =
                "androidx.navigation:navigation-dynamic-features-fragment:$VERSION"

            const val COMPOSE = "androidx.navigation:navigation-compose:$VERSION"
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

        object Compose {
            const val VERSION = "1.0.0-rc02"

            const val UI = "androidx.compose.ui:ui:$VERSION"

            // Tooling support (Previews, etc.)
            const val UI_TOOLING = "androidx.compose.ui:ui-tooling:$VERSION"

            // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
            const val FOUNDATION = "androidx.compose.foundation:foundation:$VERSION"

            // Material Design
            const val MATERIAL = "androidx.compose.material:material:$VERSION"

            // Material design icons
            const val MATERIAL_ICONS = "androidx.compose.material:material-icons-core:$VERSION"
            const val MATERIAL_ICONS_EXTENDED = "androidx.compose.material:material-icons-extended:$VERSION"

            // Integration with activities
            const val ACTIVITY = "androidx.activity:activity-compose:1.3.0-rc02"

            // Integration with ViewModels
            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"

            // Integration with observables
            const val LIVEDATA = "androidx.compose.runtime:runtime-livedata:$VERSION"
        }
    }

    object Koin {
        const val VERSION = "3.1.2"

        // Koin core features
        const val CORE = "io.insert-koin:koin-core:$VERSION"

        // Koin main features for Android (Scope,ViewModel ...)
        const val ANDROID = "io.insert-koin:koin-android:$VERSION"

        // Koin for Jetpack Compose
        const val COMPOSE = "io.insert-koin:koin-androidx-compose:$VERSION"
    }

    object Apollo {
        const val VERSION = "2.5.4"

        const val RUNTIME = "com.apollographql.apollo:apollo-runtime:$VERSION"
        const val COROUTINES_SUPPORT = "com.apollographql.apollo:apollo-coroutines-support:$VERSION"
    }
}
