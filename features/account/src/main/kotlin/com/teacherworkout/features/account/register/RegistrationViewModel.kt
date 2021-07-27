package com.teacherworkout.features.account.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teacherworkout.features.account.validators.EmailValidator
import com.teacherworkout.features.account.validators.PasswordValidator
import com.teacherworkout.features.account.validators.isValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrationViewModel : ViewModel() {

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state
    var count = 0

    // TODO actually implement the backend request when it becomes available
    // at the moment the first call fails and then succeeds to show both fail/success UIs
    fun createAccount() {
        viewModelScope.launch {
            _state.value = _state.value.copy(registrationOutcome = InProgress)
            val emailStatus = emailValidator.validate(_state.value.email)
            val passwordStatus = passwordValidator.validate(_state.value.password)
            if (emailStatus.isValid && passwordStatus.isValid) {
                delay(3000)
                if (count == 0) {
                    _state.value = _state.value.copy(registrationOutcome = Failed)
                    count++
                } else {
                    _state.value = _state.value.copy(registrationOutcome = Succeeded)
                }
            } else {
                _state.value = _state.value.copy(
                    emailStatus = emailStatus,
                    passwordStatus = passwordStatus,
                    registrationOutcome = NotInitiated
                )
            }
        }
    }

    fun setEmail(email: String) {
        val status = emailValidator.validate(email)
        _state.value = _state.value.copy(email = email, emailStatus = status)
    }

    fun setPassword(password: String) {
        val status = passwordValidator.validate(password)
        _state.value = _state.value.copy(password = password, passwordStatus = status)
    }

    fun setTosStatus(tosStatus: Boolean) {
        _state.value = _state.value.copy(hasAcceptedTos = tosStatus)
    }
}

sealed class RegistrationOutcome
object NotInitiated : RegistrationOutcome()
object InProgress : RegistrationOutcome()
object Succeeded : RegistrationOutcome()
object Failed : RegistrationOutcome()
