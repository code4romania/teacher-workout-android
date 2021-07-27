package com.teacherworkout.features.account.reset

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.features.account.register.RegisterContract
import com.teacherworkout.features.account.validators.EmailValidator
import com.teacherworkout.features.account.validators.PasswordValidator
import com.teacherworkout.features.account.validators.isValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResetPasswordViewModel :
    BaseViewModel<
            ResetPasswordContract.Event,
            ResetPasswordContract.State,
            ResetPasswordContract.Effect>() {

    private val emailValidator = EmailValidator()
    private val passwordValidator = PasswordValidator()
    private var count = 0


    override fun setInitialState(): ResetPasswordContract.State =
        ResetPasswordContract.State()

    override fun handleEvents(event: ResetPasswordContract.Event) {
        when (event) {
            is ResetPasswordContract.Event.ResetPassword -> resetAccountPassword()
            is ResetPasswordContract.Event.SetEmail -> setEmail(event.email)
            is ResetPasswordContract.Event.SetPassword -> setPassword(event.password)
        }
    }

    // TODO actually implement the backend request when it becomes available
    // at the moment the first call fails and then succeeds to show both fail/success UIs
    private fun resetAccountPassword() {
        viewModelScope.launch {
            setState { viewState.value.copy(resetOutcome = InProgress) }
            val emailStatus = emailValidator.validate(viewState.value.email)
            val passwordStatus = passwordValidator.validate(viewState.value.password)
            if (emailStatus.isValid && passwordStatus.isValid) {
                delay(3000)
                if (count == 0) {
                    setState { viewState.value.copy(resetOutcome = Failed) }
                    count++
                } else {
                    setState { viewState.value.copy(resetOutcome = Succeeded) }
                }
            } else {
                setState {
                    viewState.value.copy(
                        emailStatus = emailStatus,
                        passwordStatus = passwordStatus,
                        resetOutcome = NotInitiated
                    )
                }
            }
        }
    }

    private fun setEmail(email: String) {
        val status = emailValidator.validate(email)

        setState { viewState.value.copy(email = email, emailStatus = status) }
    }

    private fun setPassword(password: String) {
        val status = passwordValidator.validate(password)

        setState { viewState.value.copy(password = password, passwordStatus = status) }
    }
}
