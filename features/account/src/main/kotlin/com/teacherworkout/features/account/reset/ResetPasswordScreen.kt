package com.teacherworkout.features.account.reset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.teacherworkout.android.navigation.AppDestinations
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.AccountScreenScaffold
import com.teacherworkout.features.account.composables.EmailField
import com.teacherworkout.features.account.composables.PasswordField
import com.teacherworkout.features.account.composables.RegistrationLoadingUi
import com.teacherworkout.features.account.composables.RequestFailedUi
import com.teacherworkout.features.account.composables.RequestSuccessfulUi
import com.teacherworkout.features.account.validators.EmailValidationStatus
import com.teacherworkout.features.account.validators.PasswordValidationStatus
import org.koin.androidx.compose.getViewModel

@Composable
fun ResetPasswordScreen(navController: NavController, viewModel: ResetPasswordViewModel = getViewModel()) {
    val state by viewModel.state.collectAsState()
    var confirmedPassword by rememberSaveable { mutableStateOf("") }
    var confirmedPasswordHasError by rememberSaveable { mutableStateOf(false) }

    AccountScreenScaffold(titleId = R.string.reset_password_title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(text = stringResource(id = R.string.reset_password_account_notice_label))
            Spacer(modifier = Modifier.height(8.dp))
            EmailField(
                value = state.email,
                hasError = state.emailHasError,
                errorTextId = when (state.emailStatus) {
                    EmailValidationStatus.Invalid -> R.string.email_invalid_error
                    EmailValidationStatus.Valid -> R.string.empty // not visible in this case
                },
                labelTextId = R.string.input_email_label,
            ) { viewModel.setEmail(it) }
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = stringResource(id = R.string.reset_password_password_notice_label))
            Spacer(modifier = Modifier.height(8.dp))
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
                labelTextId = R.string.input_password_label,
            ) { newPassword ->
                viewModel.setPassword(newPassword)
                if (confirmedPasswordHasError) {
                    confirmedPasswordHasError = newPassword != confirmedPassword
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            PasswordField(
                value = confirmedPassword,
                enabled = state.isNotStarted,
                hasError = confirmedPasswordHasError,
                errorTextId = R.string.password_not_matching_error,
                labelTextId = R.string.input_confirm_password_label,
            ) { newConfirmedPassword ->
                confirmedPasswordHasError =
                    newConfirmedPassword != state.password && newConfirmedPassword.isNotEmpty()
                confirmedPassword = newConfirmedPassword
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (state.resetOutcome) {
                InProgress -> RegistrationLoadingUi(
                    loadingTextId = R.string.reset_password_loading_label,
                    modifier = Modifier.fillMaxWidth()
                )
                Failed -> RequestFailedUi(
                    failureTextId = R.string.reset_password_failure_label,
                    modifier = Modifier.fillMaxWidth()
                ) { viewModel.resetAccountPassword() }
                Succeeded -> RequestSuccessfulUi(
                    successTextId = R.string.reset_password_success_label,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    navController.navigate(AppDestinations.authentication) {
                        popUpTo(AppDestinations.landing)
                    }
                }
                NotInitiated -> Button(
                    enabled = state.isNotStarted,
                    onClick = {
                        if (confirmedPassword == state.password) {
                            viewModel.resetAccountPassword()
                        } else {
                            confirmedPasswordHasError = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = stringResource(id = R.string.register_btn_complete))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

