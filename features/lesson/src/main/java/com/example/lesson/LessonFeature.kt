import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.lesson.LessonStartScreen
import com.example.lesson.LessonViewModel
import com.example.lesson.di.lessonModule
import com.teacherworkout.commons.ui.navigation.AppDestinations
import org.koin.androidx.compose.getViewModel
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.lessonFeature(navHostController: NavHostController) {
    loadKoinModules(lessonModule)
    composable(
        route = AppDestinations.Lesson.Landing.route,
        arguments = listOf(
            navArgument(name = "lessonId") { type = NavType.LongType }
        )
    ) { backStackEntry ->
        val lessonId = backStackEntry.arguments?.getLong("lessonId")!!
        LessonStartScreenDestination(navHostController, lessonId)
    }
}

@Composable
private fun LessonStartScreenDestination(navHostController: NavHostController, lessonId: Long) {
    val viewModel: LessonViewModel = getViewModel { parametersOf(lessonId) }
    LessonStartScreen(
        state = viewModel.viewState.value,
        onSendEvent = { event -> viewModel.setEvent(event) },
        effects = viewModel.effect
    )
}
