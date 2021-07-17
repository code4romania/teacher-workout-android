package extensions

import com.android.build.gradle.internal.dsl.BuildType

/**
 * Extension to adds a new string field to the generated BuildConfig class.
 *
 * @param name the name of the field
 * @param value the string value of the field
 */
fun BuildType.buildConfigStringField(name: String, value: String) {
    this.buildConfigField("String", name, "\"$value\"")
}
