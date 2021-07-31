package com.teacherworkout.features.account.auth

import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.features.account.register.RegisterContract

class AuthContract {
    sealed class Event : ViewEvent {
        object Auth : Event()

        data class SetEmail(val email: String) : Event()

        data class SetPassword(val password: String) : Event()
    }

    data class State(
        val email: String = "",
        val password: String = "",
        val authOutcome: AuthOutcome = NotInitiated
    ) : ViewState

    sealed class Effect : ViewSideEffect
}

sealed class AuthOutcome
object NotInitiated : AuthOutcome()
object InProgress : AuthOutcome()
object Succeeded : AuthOutcome()
object Failed : AuthOutcome()