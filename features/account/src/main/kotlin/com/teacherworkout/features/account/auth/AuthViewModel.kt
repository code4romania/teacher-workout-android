package com.teacherworkout.features.account.auth

import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AuthViewModel : BaseViewModel<AuthContract.Event, AuthContract.State, AuthContract.Effect>() {
    companion object {
        const val SIMULATED_DELAY_MILLIS: Long = 1000
    }

    private var count = 0

    override fun setInitialState(): AuthContract.State =
        AuthContract.State()

    override fun handleEvents(event: AuthContract.Event) {
        when (event) {
            is AuthContract.Event.Auth -> auth()
            is AuthContract.Event.SetEmail -> setEmail(event.email)
            is AuthContract.Event.SetPassword -> setPassword(event.password)
        }
    }

    private fun auth() {
        viewModelScope.launch {
            setState { viewState.value.copy(authOutcome = InProgress) }
            delay(SIMULATED_DELAY_MILLIS)
            if (count == 0) {
                setState { viewState.value.copy(authOutcome = Failed) }
                count++
            } else {
                setState { viewState.value.copy(authOutcome = Succeeded) }
            }
        }
    }

    private fun setEmail(email: String) {
        setState { viewState.value.copy(email = email) }
    }

    private fun setPassword(password: String) {
        setState { viewState.value.copy(password = password) }
    }
}
