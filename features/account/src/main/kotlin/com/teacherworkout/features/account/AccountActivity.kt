package com.teacherworkout.features.account

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.teacherworkout.android.navigation.AppDestinations
import com.teacherworkout.android.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.auth.AuthenticateScreen
import com.teacherworkout.features.account.di.accountModule
import com.teacherworkout.features.account.landing.LandingScreen
import com.teacherworkout.features.account.onboarding.OnBoardingScreen
import com.teacherworkout.features.account.register.RegistrationScreen
import com.teacherworkout.features.account.reset.ResetPasswordScreen
import org.koin.core.context.loadKoinModules

class AccountActivity : AppCompatActivity() {

    @ExperimentalPagerApi
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
                        RegistrationScreen(navController)
                    }
                    composable(AppDestinations.authentication) {
                        AuthenticateScreen(navController)
                    }
                    composable(AppDestinations.reset_password) {
                        ResetPasswordScreen(navController)
                    }
                    composable(AppDestinations.on_boarding_screen) {
                        OnBoardingScreen()
                    }
                }
            }
        }
    }
}