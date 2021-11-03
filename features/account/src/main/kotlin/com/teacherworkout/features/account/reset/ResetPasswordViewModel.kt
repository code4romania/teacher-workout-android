package com.teacherworkout.features.account.reset

import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.features.account.validators.EmailValidator
import com.teacherworkout.features.account.validators.isValid
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ResetPasswordViewModel :
    BaseViewModel<
            ResetPasswordContract.Event,
            ResetPasswordContract.State,
            ResetPasswordContract.Effect>() {

    private val emailValidator = EmailValidator()
    private var count = 0

    override fun setInitialState(): ResetPasswordContract.State = ResetPasswordContract.State()

    override fun handleEvents(event: ResetPasswordContract.Event) {
        when (event) {
            is ResetPasswordContract.Event.ResetPassword -> resetAccountPassword()
            is ResetPasswordContract.Event.SetEmail -> setEmail(event.email)
        }
    }

    // TODO actually implement the backend request when it becomes available
    // at the moment the first call fails and then succeeds to show both fail/success UIs
    private fun resetAccountPassword() {
        viewModelScope.launch {
            setState { viewState.value.copy(resetOutcome = InProgress) }
            val emailStatus = emailValidator.validate(viewState.value.email)
            if (emailStatus.isValid) {
                delay(1000)
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
}
