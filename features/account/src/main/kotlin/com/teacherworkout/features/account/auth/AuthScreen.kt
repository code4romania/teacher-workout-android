package com.teacherworkout.features.account.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
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
import com.teacherworkout.features.account.composables.AccountScreenScaffold
import com.teacherworkout.features.account.composables.EmailField
import com.teacherworkout.features.account.composables.PasswordField
import com.teacherworkout.features.account.composables.RegistrationLoadingUi
import com.teacherworkout.features.account.composables.RequestFailedUi
import kotlinx.coroutines.flow.Flow

@Composable
fun AuthScreen(
    state: AuthContract.State,
    effectFlow: Flow<AuthContract.Effect>?,
    onEventSent: (event: AuthContract.Event) -> Unit,
    navController: NavHostController,
) {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    val space8dp = dimensionResource(id = R.dimen.space_8dp)
    AccountScreenScaffold(titleId = R.string.auth_title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(space16dp)
                .verticalScroll(rememberScrollState())
        ) {
            EmailField(
                value = state.email,
                labelTextId = R.string.input_email_label,
            ) { onEventSent(AuthContract.Event.SetEmail(it)) }
            Spacer(modifier = Modifier.height(space16dp))
            PasswordField(
                value = state.password,
                labelTextId = R.string.input_password_label,
            ) { onEventSent(AuthContract.Event.SetPassword(it)) }
            Spacer(modifier = Modifier.height(space8dp))
            TextButton(onClick = { navController.navigate(AppDestinations.reset_password) }) {
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
                Succeeded -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(AppDestinations.Home.landing) {
                            popUpTo(AppDestinations.Features.home) { inclusive = true }
                        }
                    }
                }
                NotInitiated -> {
                    Button(
                        onClick = { onEventSent(AuthContract.Event.Auth) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = R.string.auth_btn_auth))
                    }
                }
            }
            Spacer(modifier = Modifier.height(space16dp))
        }
    }
}
