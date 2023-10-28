package plugins

val ktlint: Configuration by configurations.creating

dependencies {
    // latest version 0.42.1 has a bug which prevents ktlint to run
    ktlint("com.pinterest:ktlint:0.40.0")
}

tasks {
    register<JavaExec>("ktlint") {
        group = BuildTasksGroups.VERIFICATION
        description = "Check Kotlin code style."
        classpath = ktlint
        // main = "com.pinterest.ktlint.Main"
        args("--android", "src/main/*.kt")
    }

    register<JavaExec>("ktlintFormat") {
        group = BuildTasksGroups.FORMATTING
        description = "Fix Kotlin code style deviations."
        classpath = ktlint
        // main = "com.pinterest.ktlint.Main"
        args("--android", "-F", "src/main/*.kt")
    }
}
