package com.teacherworkout.features.account

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.account.auth.AuthScreen
import com.teacherworkout.features.account.auth.AuthViewModel
import com.teacherworkout.features.account.di.accountModule
import com.teacherworkout.features.account.landing.LandingScreen
import com.teacherworkout.features.account.onboarding.OnBoardingScreen
import com.teacherworkout.features.account.register.RegisterScreen
import com.teacherworkout.features.account.register.RegisterViewModel
import com.teacherworkout.features.account.reset.ResetPasswordScreen
import com.teacherworkout.features.account.reset.ResetPasswordViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.loadKoinModules

fun NavGraphBuilder.accountFeature(navHostController: NavHostController) {
    loadKoinModules(accountModule)

    navigation(startDestination = AppDestinations.Account.landing, route = AppDestinations.Features.account) {
        composable(route = AppDestinations.Account.landing) {
            LandingScreen(navHostController)
        }
        composable(AppDestinations.Account.registration) {
            RegisterScreenDestination(navHostController)
        }
        composable(AppDestinations.Account.authentication) {
            AuthScreenDestination(navHostController)
        }
        composable(AppDestinations.Account.reset_password) {
            ResetPasswordScreenDestination(navHostController)
        }

        composable(AppDestinations.Account.onboarding) {
            OnBoardingScreen {
                navHostController.navigate(AppDestinations.Features.home) {
                    popUpTo(AppDestinations.Account.landing)
                }
            }
        }
    }
}

@Composable
private fun RegisterScreenDestination(navHostController: NavHostController) {
    val viewModel: RegisterViewModel = getViewModel()

    RegisterScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        navController = navHostController
    )
}

@Composable
private fun AuthScreenDestination(navHostController: NavHostController) {
    val viewModel: AuthViewModel = getViewModel()

    AuthScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        navController = navHostController
    )
}

@Composable
private fun ResetPasswordScreenDestination(navHostController: NavHostController) {
    val viewModel: ResetPasswordViewModel = getViewModel()

    ResetPasswordScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        navController = navHostController
    )
}
