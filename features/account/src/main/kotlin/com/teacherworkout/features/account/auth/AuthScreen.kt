package com.teacherworkout.features.account.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.*

@Composable
fun AuthScreen(
    state: AuthContract.State,
    onEventSent: (event: AuthContract.Event) -> Unit,
    navController: NavHostController,
) {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val minTouchSize = dimensionResource(id = R.dimen.min_touch_size)

    AccountScreenScaffold(titleId = R.string.auth_title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space16dp)
                .verticalScroll(rememberScrollState())
        ) {
            EmailField(state, onEventSent)
            PasswordField(state, onEventSent)
            TextButton(
                onClick = { navController.navigate(AppDestinations.Account.ResetPassword.route) },
                shape = RoundedCornerShape(50),
                modifier = Modifier.heightIn(min = minTouchSize)
            ) {
                Text(text = stringResource(id = R.string.auth_btn_forgot_password))
            }
            Spacer(modifier = Modifier.height(space16dp))
            when (state.authOutcome) {
                InProgress -> RegistrationLoadingUi(
                    loadingTextId = R.string.auth_loading_label,
                    modifier = Modifier.fillMaxWidth()
                )
                Failed -> RequestFailedUi(
                    failureTextId = R.string.auth_failure_label,
                    modifier = Modifier.fillMaxWidth()
                ) { onEventSent(AuthContract.Event.Auth) }
                Succeeded -> LaunchedEffect(Unit) {
                    navController.navigate(AppDestinations.Account.Onboarding.route)
                }
                NotInitiated -> Button(
                    shape = RoundedCornerShape(50),
                    onClick = { onEventSent(AuthContract.Event.Auth) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = minTouchSize)
                ) {
                    Text(text = stringResource(id = R.string.auth_btn_auth))
                }
            }
            Spacer(modifier = Modifier.height(space16dp))
        }
    }
}

@Composable
private fun PasswordField(
    state: AuthContract.State,
    onEventSent: (event: AuthContract.Event) -> Unit
) {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space8dp = dimensionResource(id = R.dimen.space_8dp)

    Text(text = stringResource(id = R.string.input_password_label))
    Spacer(modifier = Modifier.height(space16dp))
    PasswordField(
        value = state.password,
        labelTextId = R.string.input_password_placeholder,
    ) { onEventSent(AuthContract.Event.SetPassword(it)) }
    Spacer(modifier = Modifier.height(space8dp))
}

@Composable
private fun EmailField(
    state: AuthContract.State,
    onEventSent: (event: AuthContract.Event) -> Unit
) {
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    val space24dp = dimensionResource(id = R.dimen.space_24dp)

    Text(text = stringResource(id = R.string.input_email_label))
    Spacer(modifier = Modifier.height(space8dp))
    EmailField(
        value = state.email,
        labelTextId = R.string.input_email_placeholder,
    ) { onEventSent(AuthContract.Event.SetEmail(it)) }
    Spacer(modifier = Modifier.height(space24dp))
}
