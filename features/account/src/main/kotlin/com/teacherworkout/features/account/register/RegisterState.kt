package com.teacherworkout.features.account.register

import com.teacherworkout.features.account.validators.EmailValidationStatus
import com.teacherworkout.features.account.validators.PasswordValidationStatus

data class RegisterState(
    val email: String = "",
    val emailStatus: EmailValidationStatus = EmailValidationStatus.Valid,
    val password: String = "",
    val passwordStatus: PasswordValidationStatus = PasswordValidationStatus.Valid,
    val hasAcceptedTos: Boolean = false,
    val registrationOutcome: RegistrationOutcome = NotInitiated,
)

val RegisterState.emailHasError
    get() = emailStatus != EmailValidationStatus.Valid

val RegisterState.passwordHasError
    get() = passwordStatus != PasswordValidationStatus.Valid

val RegisterState.isNotStarted
    get() = this.registrationOutcome == NotInitiated