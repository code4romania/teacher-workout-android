package com.teacherworkout.features.account.reset

import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.features.account.validators.EmailValidationStatus

class ResetPasswordContract {
    sealed class Event : ViewEvent {
        object ResetPassword : Event()

        data class SetEmail(val email: String) : Event()
    }

    data class State(
        val email: String = "",
        val emailStatus: EmailValidationStatus = EmailValidationStatus.Valid,
        val resetOutcome: ResetPasswordOutcome = NotInitiated,
    ) : ViewState

    sealed class Effect : ViewSideEffect
}

val ResetPasswordContract.State.emailHasError
    get() = emailStatus != EmailValidationStatus.Valid

val ResetPasswordContract.State.isNotStarted
    get() = this.resetOutcome == NotInitiated

sealed class ResetPasswordOutcome
object NotInitiated : ResetPasswordOutcome()
object InProgress : ResetPasswordOutcome()
object Succeeded : ResetPasswordOutcome()
object Failed : ResetPasswordOutcome()
