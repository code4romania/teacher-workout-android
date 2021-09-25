package com.teacherworkout.android

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
import com.teacherworkout.features.account.accountFeature

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()
            TeacherWorkoutTheme {
                NavHost(navController = navHostController, startDestination = AppDestinations.Features.account) {
                    accountFeature(navHostController)

                    composable(AppDestinations.Features.home) {
                        HomeScreen(navHostController)
                    }
                }
            }
        }
    }

    @Composable
    private fun HomeScreen(navHostController: NavHostController) {
    }
}
