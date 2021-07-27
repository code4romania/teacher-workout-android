package com.teacherworkout.features.account.validators

import com.teacherworkout.features.account.validators.EmailValidationStatus.Invalid
import com.teacherworkout.features.account.validators.EmailValidationStatus.Valid

/**
 * Simple validator for email introduced by the user, this simple checks for the presence of a "@" character and a
 * "." in the email domain part.
 */
class EmailValidator : FieldValidator<EmailValidationStatus> {

    override fun validate(input: String): EmailValidationStatus {
        val emailParts = input.split("@")
        if (emailParts.size != 2) {
            return Invalid
        } else {
            val arePartsEmpty = emailParts[0].isEmpty() || emailParts[1].isEmpty()
            val hasDomainPoint = emailParts[1].contains(".")
            val hasDomainPartsEmpty = emailParts[1].split(".").any { it.isEmpty() }
            if (arePartsEmpty || !hasDomainPoint || hasDomainPartsEmpty) {
                return Invalid
            }
        }
        return Valid
    }
}

sealed class EmailValidationStatus {
    object Valid : EmailValidationStatus()
    object Invalid : EmailValidationStatus()
}

val EmailValidationStatus.isValid
    get() = this == Valid
