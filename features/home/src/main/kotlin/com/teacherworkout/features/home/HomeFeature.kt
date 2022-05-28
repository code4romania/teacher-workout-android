package com.teacherworkout.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.teacherworkout.commons.ui.composables.BottomBarScaffold
import com.teacherworkout.commons.ui.navigation.AppDestinations
import com.teacherworkout.features.home.di.homeModule
import com.teacherworkout.features.home.landing.LandingContract
import com.teacherworkout.features.home.landing.LandingViewModel
import com.teacherworkout.features.home.landing.LandingScreen
import com.teacherworkout.features.discover.discoverFeature
import com.teacherworkout.features.lesson.lessonFeature
import com.teacherworkout.features.profile.profileFeature
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules

fun NavGraphBuilder.homeFeature(navHostController: NavHostController) {
    loadKoinModules(homeModule)
    navigation(
        startDestination = AppDestinations.Home.Landing.route,
        route = AppDestinations.Features.home
    ) {
        landingFeature(navHostController)
        discoverFeature(navHostController)
        profileFeature(navHostController)

        lessonFeature(navHostController)
    }
}

private fun NavGraphBuilder.landingFeature(navHostController: NavHostController) {
    composable(route = AppDestinations.Home.Landing.route) {
        HomeScreenDestination(navHostController)
    }
}

@Composable
private fun HomeScreenDestination(navHostController: NavHostController) {
    val viewModel: LandingViewModel = getViewModel()
    BottomBarScaffold(navHostController) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LandingScreen(
                state = viewModel.viewState.value,
                sendEvent = { event -> viewModel.setEvent(event) },
                effects = viewModel.effect,
                onNavigationRequest = { navigationEffect: LandingContract.Effect.Navigation ->
                    when (navigationEffect) {
                        is LandingContract.Effect.Navigation.ToLessonDetails -> {
                            val lessonId = navigationEffect.lessonId
                            navHostController.navigate("home-lesson-landing/${lessonId}")
                        }
                    }
                }
            )
        }
    }
}
