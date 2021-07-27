package com.teacherworkout.features.account.register

import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.features.account.validators.EmailValidationStatus
import com.teacherworkout.features.account.validators.PasswordValidationStatus

class RegisterContract {
    sealed class Event : ViewEvent {
        object CreateAccount : Event()

        data class SetEmail(val email: String) : Event()

        data class SetPassword(val password: String) : Event()

        data class SetTos(val newStatus: Boolean) : Event()
    }

    data class State(
        val email: String = "",
        val emailStatus: EmailValidationStatus = EmailValidationStatus.Valid,
        val password: String = "",
        val passwordStatus: PasswordValidationStatus = PasswordValidationStatus.Valid,
        val hasAcceptedTos: Boolean = false,
        val registrationOutcome: RegistrationOutcome = NotInitiated,
    ) : ViewState

    sealed class Effect : ViewSideEffect
}

val RegisterContract.State.emailHasError
    get() = emailStatus != EmailValidationStatus.Valid

val RegisterContract.State.passwordHasError
    get() = passwordStatus != PasswordValidationStatus.Valid

val RegisterContract.State.isNotStarted
    get() = this.registrationOutcome == NotInitiated

sealed class RegistrationOutcome
object NotInitiated : RegistrationOutcome()
object InProgress : RegistrationOutcome()
object Succeeded : RegistrationOutcome()
object Failed : RegistrationOutcome()
