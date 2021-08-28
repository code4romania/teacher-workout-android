package com.teacherworkout.features.account

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.commons.ui.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.auth.AuthScreen
import com.teacherworkout.features.account.auth.AuthViewModel
import com.teacherworkout.features.account.di.accountModule
import com.teacherworkout.features.account.landing.LandingScreen
import com.teacherworkout.features.account.register.RegisterScreen
import com.teacherworkout.features.account.register.RegisterViewModel
import com.teacherworkout.features.account.reset.ResetPasswordScreen
import com.teacherworkout.features.account.reset.ResetPasswordViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.loadKoinModules

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        loadKoinModules(accountModule)
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TeacherWorkoutTheme {
                NavHost(navController = navController, startDestination = AppDestinations.landing) {
                    composable(AppDestinations.landing) {
                        LandingScreen(navController)
                    }
                    composable(AppDestinations.registration) {
                        RegisterScreenDestination(navController)
                    }
                    composable(AppDestinations.authentication) {
                        AuthScreenDestination(navController)
                    }
                    composable(AppDestinations.reset_password) {
                        ResetPasswordScreenDestination(navController)
                    }
                }
            }
        }
    }

    @Composable
    private fun RegisterScreenDestination(navController: NavHostController) {
        val viewModel: RegisterViewModel = getViewModel()

        RegisterScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            navController = navController
        )
    }

    @Composable
    private fun AuthScreenDestination(navController: NavHostController) {
        val viewModel: AuthViewModel = getViewModel()

        AuthScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            navController = navController
        )
    }

    @Composable
    private fun ResetPasswordScreenDestination(navController: NavHostController) {
        val viewModel: ResetPasswordViewModel = getViewModel()

        ResetPasswordScreen(
            state = viewModel.viewState.value,
            effectFlow = viewModel.effect,
            onEventSent = { event -> viewModel.setEvent(event) },
            navController = navController
        )
    }
}