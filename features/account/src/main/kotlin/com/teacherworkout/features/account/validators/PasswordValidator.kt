package com.teacherworkout.features.account.validators

import com.teacherworkout.features.account.validators.PasswordValidationStatus.NoDigit
import com.teacherworkout.features.account.validators.PasswordValidationStatus.NoLowercase
import com.teacherworkout.features.account.validators.PasswordValidationStatus.NoSpecialChar
import com.teacherworkout.features.account.validators.PasswordValidationStatus.NoUppercase
import com.teacherworkout.features.account.validators.PasswordValidationStatus.TooShort
import com.teacherworkout.features.account.validators.PasswordValidationStatus.Valid

/**
 * Simple validator for passwords introduced by the user. The password should be at least 8 characters and must
 * contain at least 1 lowercase, 1 uppercase, 1 digit and 1 special character.
 */
class PasswordValidator : FieldValidator<PasswordValidationStatus> {
    override fun validate(input: String): PasswordValidationStatus {
        if (input.length < 8) {
            return TooShort
        } else {
            var hasLowercase = false
            var hasUppercase = false
            var hasDigit = false
            var hasSpecialChar = false
            input.forEach {
                if (it.isLowerCase()) hasLowercase = true
                if (it.isUpperCase()) hasUppercase = true
                if (it.isDigit()) hasDigit = true
                if ("`~!@#$%^&*()_+-={}|[]\\;:'\"<>?,./".contains(it)) hasSpecialChar = true
            }
            if (!hasLowercase) return NoLowercase
            if (!hasUppercase) return NoUppercase
            if (!hasDigit) return NoDigit
            if (!hasSpecialChar) return NoSpecialChar
        }
        return Valid
    }
}

sealed class PasswordValidationStatus {
    object Valid : PasswordValidationStatus()
    object TooShort : PasswordValidationStatus()
    object NoLowercase : PasswordValidationStatus()
    object NoUppercase : PasswordValidationStatus()
    object NoDigit : PasswordValidationStatus()
    object NoSpecialChar : PasswordValidationStatus()
}

val PasswordValidationStatus.isValid
    get() = this == Valid
