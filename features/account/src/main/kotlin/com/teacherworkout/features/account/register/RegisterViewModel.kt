package com.teacherworkout.features.account.register

import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.features.account.validators.EmailValidator
import com.teacherworkout.features.account.validators.PasswordValidator
import com.teacherworkout.features.account.validators.isValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel :
    BaseViewModel<RegisterContract.Event, RegisterContract.State, RegisterContract.Effect>() {

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    var count = 0

    override fun setInitialState(): RegisterContract.State =
        RegisterContract.State()

    override fun handleEvents(event: RegisterContract.Event) {
        when (event) {
            is RegisterContract.Event.CreateAccount -> createAccount()
            is RegisterContract.Event.SetEmail -> setEmail(event.email)
            is RegisterContract.Event.SetPassword -> setPassword(event.password)
            is RegisterContract.Event.SetTos -> setTosStatus(event.newStatus)
        }
    }

    // TODO actually implement the backend request when it becomes available
    // at the moment the first call fails and then succeeds to show both fail/success UIs
    private fun createAccount() {
        viewModelScope.launch {
            setState {
                viewState.value.copy(registrationOutcome = InProgress)
            }

            val emailStatus = emailValidator.validate(viewState.value.email)
            val passwordStatus = passwordValidator.validate(viewState.value.password)

            if (emailStatus.isValid && passwordStatus.isValid) {
                delay(1000)
                if (count == 0) {
                    setState {
                        viewState.value.copy(registrationOutcome = Failed)
                    }
                    count++
                } else {
                    setState {
                        viewState.value.copy(registrationOutcome = Succeeded)
                    }
                }
            } else {
                setState {
                    viewState.value.copy(
                        emailStatus = emailStatus,
                        passwordStatus = passwordStatus,
                        registrationOutcome = NotInitiated
                    )
                }
            }
        }
    }

    private fun setEmail(email: String) {
        val status = emailValidator.validate(email)

        setState {
            viewState.value.copy(email = email, emailStatus = status)
        }
    }

    private fun setPassword(password: String) {
        val status = passwordValidator.validate(password)

        setState {
            viewState.value.copy(password = password, passwordStatus = status)
        }
    }

    private fun setTosStatus(tosStatus: Boolean) {
        setState {
            viewState.value.copy(hasAcceptedTos = tosStatus)
        }
    }
}
