package com.teacherworkout.features.account.reset

import com.teacherworkout.features.account.validators.EmailValidationStatus
import com.teacherworkout.features.account.validators.PasswordValidationStatus

data class ResetPasswordState(
    val email: String = "",
    val emailStatus: EmailValidationStatus = EmailValidationStatus.Valid,
    val password: String = "",
    val passwordStatus: PasswordValidationStatus = PasswordValidationStatus.Valid,
    val hasAcceptedTos: Boolean = false,
    val resetOutcome: ResetPasswordOutcome = NotInitiated,
)

val ResetPasswordState.emailHasError
    get() = emailStatus != EmailValidationStatus.Valid

val ResetPasswordState.passwordHasError
    get() = passwordStatus != PasswordValidationStatus.Valid

val ResetPasswordState.isNotStarted
    get() = this.resetOutcome == NotInitiated