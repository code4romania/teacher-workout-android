package extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import dependencies.TestDependencies
import dependencies.TestAndroidDependencies

fun DependencyHandler.implementation(dependencyNotation: String): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.testImplementation(dependencyNotation: String): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: String): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.addTestDependencies() {
    testImplementation(TestDependencies.JUNIT)
    testImplementation(TestDependencies.MOCKITO)
    testImplementation(TestDependencies.TRUTH)
    testImplementation(TestDependencies.AndroidX.CORE_TESTING)
    testImplementation(TestDependencies.COROUTINES_TEST)

    androidTestImplementation(TestAndroidDependencies.AndroidX.ESPRESSO_CORE)
    androidTestImplementation(TestAndroidDependencies.AndroidX.JUNIT)
}
