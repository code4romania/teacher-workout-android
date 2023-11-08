package com.teacherworkout.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.commons.ui.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.accountFeature
import com.teacherworkout.features.home.homeFeature

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navHostController = rememberNavController()

            TeacherWorkoutTheme {
                Scaffold { paddingValues ->
                    NavHost(
                        navController = navHostController,
                        startDestination = AppDestinations.Features.home,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        accountFeature(navHostController)
                        homeFeature(navHostController)
                    }
                }
            }
        }
    }
}
