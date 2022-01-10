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
import com.teacherworkout.features.home.lessons.HomeContract
import com.teacherworkout.features.home.lessons.HomeViewModel
import com.teacherworkout.features.home.lessons.LandingScreen
import com.teacherworkout.features.learn.learnFeature
import com.teacherworkout.features.profile.profileFeature
import com.teacherworkout.features.lesson.lessonFeature
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules

fun NavGraphBuilder.homeFeature(navHostController: NavHostController) {
    loadKoinModules(homeModule)
    navigation(
        startDestination = AppDestinations.Home.Landing.route,
        route = AppDestinations.Features.home
    ) {
        composable(route = AppDestinations.Home.Landing.route) {
            HomeScreenDestination(navHostController)
        }
        learnFeature(navHostController)
        profileFeature(navHostController)
        lessonFeature(navHostController)
    }
}

@Composable
private fun HomeScreenDestination(navHostController: NavHostController) {
    val viewModel: HomeViewModel = getViewModel()
    BottomBarScaffold(navHostController) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            LandingScreen(
                state = viewModel.viewState.value,
                onSendEvent = { event -> viewModel.setEvent(event) },
                effects = viewModel.effect,
                onNavigationRequest = { navigationEffect: HomeContract.Effect.Navigation ->
                    when (navigationEffect) {
                        is HomeContract.Effect.Navigation.ToLessonDetails -> {
                            val lessonId = navigationEffect.lessonId
                            navHostController.navigate("home-lesson-landing/${lessonId}")
                        }
                    }
                }
            )
        }
    }
}
