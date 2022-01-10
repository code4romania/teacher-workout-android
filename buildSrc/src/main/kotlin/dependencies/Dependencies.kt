package dependencies

object Dependencies {
    const val OKHTTP = "com.squareup.okhttp3:okhttp:4.9.1"
    const val MATERIAL = "com.google.android.material:material:1.4.0"
    const val LOTTIE = "com.airbnb.android:lottie:4.2.0"
    const val COIL = "io.coil-kt:coil:1.4.0"
    const val COIL_COMPOSE = "io.coil-kt:coil-compose:1.4.0"

    object Coroutines {
        const val VERSION = "1.5.2"

        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$VERSION"
        const val ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$VERSION"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:1.6.0"
        const val APPCOMPAT = "androidx.appcompat:appcompat:1.3.1"

        object Navigation {
            private const val VERSION = "2.4.0-alpha10"

            const val UI = "androidx.navigation:navigation-ui-ktx:$VERSION"
            const val COMPOSE = "androidx.navigation:navigation-compose:$VERSION"
        }

        object Lifecycle {
            private const val VERSION = "2.4.0-rc01"

            const val VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:$VERSION"
            // ViewModel utilities for Compose
            const val VIEWMODEL_COMPOSE = "androidx.lifecycle:lifecycle-viewmodel-compose:$VERSION"
            const val LIVEDATA = "androidx.lifecycle:lifecycle-livedata-ktx:$VERSION"
            // Lifecycles only (without ViewModel or LiveData)
            const val RUNTIME_KTX =  "androidx.lifecycle:lifecycle-runtime-ktx:$VERSION"
            // if using Java8, use the following instead of lifecycle-compiler
            const val COMMON_JAVA8 = "androidx.lifecycle:lifecycle-common-java8:$VERSION"
        }

        object Paging {
            private const val VERSION = "3.0.1"

            const val RUNTIME = "androidx.paging:paging-runtime-ktx:$VERSION"
        }

        object Compose {
            const val VERSION = "1.0.4"
            private const val ACCOMPANIST_PAGER_VERSION = "0.20.0"
            private const val CONSTRAINTLAYOUT_VERSION = "1.0.0-rc02"

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
            const val ACTIVITY = "androidx.activity:activity-compose:1.3.1"

            // Integration with observables
            const val LIVEDATA = "androidx.compose.runtime:runtime-livedata:$VERSION"

            const val PAGER = "com.google.accompanist:accompanist-pager:$ACCOMPANIST_PAGER_VERSION"

            const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:$CONSTRAINTLAYOUT_VERSION"
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

        const val VIEWMODEL = "io.insert-koin:koin-android-viewmodel:$VERSION"
    }

    object Apollo {
        const val VERSION = "2.5.4"

        const val RUNTIME = "com.apollographql.apollo:apollo-runtime:$VERSION"
        const val COROUTINES_SUPPORT = "com.apollographql.apollo:apollo-coroutines-support:$VERSION"
    }
}
