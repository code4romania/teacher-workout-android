package com.teacherworkout.features.account.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state
    var count = 0

    // TODO connect with backend, at the moment the call just fails and the succeeds on retry
    fun auth() {
        viewModelScope.launch {
            _state.value = _state.value.copy(authOutcome = InProgress)
            delay(3000)
            if (count == 0) {
                _state.value = _state.value.copy(authOutcome = Failed)
                count++
            } else {
                _state.value = _state.value.copy(authOutcome = Succeeded)
            }
        }
    }

    fun setEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun setPassword(password: String) {
        _state.value = _state.value.copy(password = password)
    }
}

sealed class AuthOutcome
object NotInitiated : AuthOutcome()
object InProgress : AuthOutcome()
object Succeeded : AuthOutcome()
object Failed : AuthOutcome()
