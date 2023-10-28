object BuildAndroidConfig {
    const val APPLICATION_ID = "com.teacherworkout.android"

    const val COMPILE_SDK_VERSION = 31
    const val MIN_SDK_VERSION = 24
    const val TARGET_SDK_VERSION = 31

    private const val VERSION_MAJOR = 1
    private const val VERSION_MINOR = 0
    private const val VERSION_PATCH = 0
    private const val VERSION_BUILD = 0

    const val VERSION_CODE =
        VERSION_MAJOR * 10000 + VERSION_MINOR * 1000 + VERSION_PATCH * 100 + VERSION_BUILD
    const val VERSION_NAME = "${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_PATCH}"

    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"
    val TEST_INSTRUMENTATION_RUNNER_ARGUMENTS = mapOf(
        "leakcanary.FailTestOnLeakRunListener" to "listener"
    )
}
