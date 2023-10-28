package com.teacherworkout.features.account.reset

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.AccountScreenScaffold
import com.teacherworkout.features.account.composables.EmailField
import com.teacherworkout.features.account.composables.RegistrationLoadingUi
import com.teacherworkout.features.account.composables.RequestFailedUi
import com.teacherworkout.features.account.validators.EmailValidationStatus

@Composable
fun ResetPasswordScreen(
    state: ResetPasswordContract.State,
    onEventSent: (event: ResetPasswordContract.Event) -> Unit,
    navController: NavController,
) {
    val space8dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_8dp)
    val space16dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_16dp)
    val minTouchSize = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.min_touch_size)

    AccountScreenScaffold(titleId = R.string.reset_password_title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space16dp)
        ) {
            Text(text = stringResource(id = R.string.input_email_label))
            Spacer(modifier = Modifier.height(space8dp))
            EmailField(
                value = state.email,
                hasError = state.emailHasError,
                errorTextId = when (state.emailStatus) {
                    EmailValidationStatus.Invalid -> R.string.email_invalid_error
                    EmailValidationStatus.Valid -> R.string.empty // not visible in this case
                },
                labelTextId = R.string.input_email_label,
            ) { onEventSent(ResetPasswordContract.Event.SetEmail(it)) }
            Spacer(modifier = Modifier.height(space16dp))
            when (state.resetOutcome) {
                InProgress -> RegistrationLoadingUi(
                    loadingTextId = R.string.reset_password_loading_label,
                    modifier = Modifier.fillMaxWidth()
                )
                Failed -> RequestFailedUi(
                    failureTextId = R.string.reset_password_failure_label,
                    modifier = Modifier.fillMaxWidth()
                ) { onEventSent(ResetPasswordContract.Event.ResetPassword) }
                Succeeded ->
                    LaunchedEffect(Unit) {
                        navController.navigate(AppDestinations.Account.ResetPasswordSucceeded.route) {
                            popUpTo(AppDestinations.Account.Authentication.route)
                        }
                    }
                NotInitiated -> Button(
                    shape = MaterialTheme.shapes.large,
                    enabled = state.isNotStarted,
                    onClick = { onEventSent(ResetPasswordContract.Event.ResetPassword) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = minTouchSize)
                ) {
                    Text(text = stringResource(id = R.string.reset_password_btn_complete))
                }
            }
            Spacer(modifier = Modifier.height(space16dp))
        }
    }
}
