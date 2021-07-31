package com.teacherworkout.features.account

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.teacherworkout.android.navigation.AppDestinations
import com.teacherworkout.android.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.auth.AuthenticateScreen
import com.teacherworkout.features.account.di.accountModule
import com.teacherworkout.features.account.landing.LandingScreen
import com.teacherworkout.features.account.register.RegisterScreen
import com.teacherworkout.features.account.register.RegisterViewModel
import com.teacherworkout.features.account.reset.ResetPasswordScreen
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.loadKoinModules

class AccountActivity : AppCompatActivity() {

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
                        AuthenticateScreen(navController)
                    }
                    composable(AppDestinations.reset_password) {
                        ResetPasswordScreen(navController)
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
}