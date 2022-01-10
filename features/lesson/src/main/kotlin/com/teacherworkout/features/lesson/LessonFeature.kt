package com.teacherworkout.features.lesson

import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.teacherworkout.features.lesson.di.lessonModule
import com.teacherworkout.commons.ui.navigation.AppDestinations
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.parameter.parametersOf

//TODO: add a navigation graph instead of a simple `composable`
fun NavGraphBuilder.lessonFeature(navHostController: NavHostController) {
    loadKoinModules(lessonModule)
    composable(
        route = AppDestinations.Lesson.Landing.route,
        //TODO: make the nav argument to not be hard coded
        arguments = listOf(
            navArgument(name = "lessonId") { type = NavType.LongType }
        )
    ) { backStackEntry ->
        val lessonId = backStackEntry.arguments?.getLong("lessonId")!!
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
                is LessonContract.Effect.Navigation.NavigateUp -> {
                    navController.popBackStack()
                }
            }
        }
    )
}
