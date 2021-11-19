package com.teacherworkout.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.learn.learnFeature
import com.teacherworkout.features.profile.profileFeature

fun NavGraphBuilder.homeFeature(navHostController: NavHostController) {
    navigation(
        startDestination = AppDestinations.Home.Landing.route,
        route = AppDestinations.Features.home
    ) {
        composable(route = AppDestinations.Home.Landing.route) {
            //without applying the inner padding the bottom bar would overlap our content
            BottomBarScaffold(navHostController) { innerPadding ->
                Box(modifier = Modifier.padding(innerPadding)) {
                    LandingScreen()
                }
            }
        }
        learnFeature(navHostController)
        profileFeature(navHostController)
    }
}
