package com.teacherworkout.features.lesson

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.teacherworkout.features.lesson.di.lessonModule
import com.teacherworkout.commons.ui.navigation.AppDestinations
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.lessonFeature(navHostController: NavHostController) {
    loadKoinModules(lessonModule)

    val lessonIdArgumentName = "lessonId"
    composable(
        route = AppDestinations.Lesson.Landing.route,
        arguments = listOf(
            navArgument(name = lessonIdArgumentName) { type = NavType.LongType }
        )
    ) { backStackEntry ->
        val lessonId = backStackEntry.arguments?.getLong(lessonIdArgumentName)!!
        LessonStartScreenDestination(navHostController, lessonId)
    }
}

@Composable
private fun LessonStartScreenDestination(navController: NavController, lessonId: Long) {
    val viewModel: LessonViewModel = getViewModel { parametersOf(lessonId) }
    LessonStartScreen(
        state = viewModel.viewState.value,
        onSendEvent = { event -> viewModel.setEvent(event) },
        effects = viewModel.effect,
        onNavigationRequest = { navRequest ->
            when(navRequest) {
                is LessonContract.Effect.Navigation.NavigateUp -> navController.popBackStack()
            }
        }
    )
}
