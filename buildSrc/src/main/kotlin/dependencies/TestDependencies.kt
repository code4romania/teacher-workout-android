package dependencies

object TestDependencies {
    const val JUNIT = "junit:junit:4.13.2"

    const val MOCKITO = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0"

    const val TRUTH = "com.google.truth:truth:1.1.2"

    const val KOIN = "org.koin:koin-test:${Dependencies.Koin.VERSION}"

    const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Dependencies.Coroutines.VERSION}"

    object AndroidX {
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.3.0"

        const val JUNIT = "androidx.test.ext:junit:1.1.3"

        const val CORE_TESTING = "androidx.arch.core:core-testing:2.1.0"
    }
}