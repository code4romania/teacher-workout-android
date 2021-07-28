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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.teacherworkout.android.navigation.AppDestinations
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.AccountScreenScaffold
import com.teacherworkout.features.account.composables.EmailField
import com.teacherworkout.features.account.composables.PasswordField
import com.teacherworkout.features.account.composables.RegistrationLoadingUi
import com.teacherworkout.features.account.composables.RequestFailedUi
import org.koin.androidx.compose.getViewModel

@Composable
fun AuthenticateScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = getViewModel()
) {
    val state by viewModel.state.collectAsState()
    val space16dp = dimensionResource(id = com.teacherworkout.android.R.dimen.space_16dp)
    val space8dp = dimensionResource(id = com.teacherworkout.android.R.dimen.space_8dp)
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
            ) { viewModel.setEmail(it) }
            Spacer(modifier = Modifier.height(space16dp))
            PasswordField(
                value = state.password,
                labelTextId = R.string.input_password_label,
            ) { viewModel.setPassword(it) }
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
                ) { viewModel.auth() }
                Succeeded -> { /* the user is sent to onboarding/main screen */
                    navController.navigate(AppDestinations.on_boarding_screen)
                }
                NotInitiated -> {
                    Button(
                        onClick = { viewModel.auth() },
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
