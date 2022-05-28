package com.teacherworkout.features.account.register

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.*
import com.teacherworkout.features.account.validators.PasswordValidationStatus

@Composable
fun RegisterScreen(
    state: RegisterContract.State,
    onEventSent: (event: RegisterContract.Event) -> Unit,
    navController: NavHostController,
) {
    val confirmedPassword by rememberSaveable { mutableStateOf("") }
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

    AccountScreenScaffold(titleId = R.string.register_title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space16dp)
                .verticalScroll(rememberScrollState())
        ) {
            EmailField(state, onEventSent)
            PasswordFields(state, onEventSent)
            TermsAndConditions(
                isAccepted = state.hasAcceptedTos,
                hasError = !state.hasAcceptedTos,
            ) { newStatus ->
                onEventSent(RegisterContract.Event.SetTos(newStatus))
            }
            Spacer(modifier = Modifier.height(space24dp))
            when (state.registrationOutcome) {
                InProgress -> RegistrationLoadingUi(
                    loadingTextId = R.string.register_loading_label,
                    modifier = Modifier.fillMaxWidth()
                )
                Failed -> RequestFailedUi(
                    failureTextId = R.string.register_failure_label,
                    modifier = Modifier.fillMaxWidth()
                ) { onEventSent(RegisterContract.Event.CreateAccount) }
                Succeeded -> RequestSuccessfulUi(
                    successTextId = R.string.register_success_label,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    navController.navigate(AppDestinations.Account.Authentication.route) {
                        popUpTo(AppDestinations.Account.Landing.route)
                    }
                }
                NotInitiated -> Button(
                    shape = RoundedCornerShape(50),
                    enabled = state.isNotStarted,
                    onClick = {
                        if (state.hasAcceptedTos && confirmedPassword == state.password) {
                            onEventSent(RegisterContract.Event.CreateAccount)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = minTouchSize)
                ) {
                    Text(text = stringResource(id = R.string.register_btn_complete))
                }
            }
            Spacer(modifier = Modifier.height(space16dp))
        }
    }
}

@Composable
private fun PasswordFields(
    state: RegisterContract.State,
    onEventSent: (event: RegisterContract.Event) -> Unit,
) {
    var confirmedPassword by rememberSaveable { mutableStateOf("") }
    var confirmedPasswordHasError by rememberSaveable { mutableStateOf(false) }

    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)

    Text(text = stringResource(id = R.string.input_password_label))
    Spacer(modifier = Modifier.height(space8dp))
    PasswordField(
        value = state.password,
        hasError = state.passwordHasError,
        errorTextId = when (state.passwordStatus) {
            PasswordValidationStatus.NoDigit -> R.string.password_no_digit_error
            PasswordValidationStatus.NoLowercase -> R.string.password_no_lowercase_error
            PasswordValidationStatus.NoSpecialChar -> R.string.password_no_special_char_error
            PasswordValidationStatus.NoUppercase -> R.string.password_no_uppercase_error
            PasswordValidationStatus.TooShort -> R.string.password_too_short_error
            PasswordValidationStatus.Valid -> R.string.empty // not visible in this case
        },
        labelTextId = R.string.input_password_placeholder,
    ) { newPassword ->
        onEventSent(RegisterContract.Event.SetPassword(newPassword))
        if (confirmedPasswordHasError) {
            confirmedPasswordHasError = newPassword != confirmedPassword
        }
    }
    Spacer(modifier = Modifier.height(space24dp))
    Text(text = stringResource(id = R.string.input_confirm_password_label))
    Spacer(modifier = Modifier.height(space8dp))
    PasswordField(
        value = confirmedPassword,
        hasError = confirmedPasswordHasError,
        errorTextId = R.string.password_not_matching_error,
        labelTextId = R.string.input_password_placeholder,
    ) { newConfirmedPassword ->
        confirmedPasswordHasError =
            newConfirmedPassword != state.password && newConfirmedPassword.isNotEmpty()
        confirmedPassword = newConfirmedPassword
    }
    Spacer(modifier = Modifier.height(space8dp))
}

@Composable
private fun EmailField(
    state: RegisterContract.State,
    onEventSent: (event: RegisterContract.Event) -> Unit
) {
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)

    Text(text = stringResource(id = R.string.input_email_label))
    Spacer(modifier = Modifier.height(space8dp))
    EmailField(
        value = state.email,
        hasError = state.emailHasError,
        errorTextId = R.string.email_invalid_error,
        labelTextId = R.string.input_email_placeholder,
    ) { onEventSent(RegisterContract.Event.SetEmail(it)) }
    Spacer(modifier = Modifier.height(space24dp))
}
