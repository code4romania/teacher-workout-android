package com.example.lesson

import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.features.lesson.R

//TODO: properly implement the LessonViewModel
class LessonViewModel(
    private val lessonId: Long
): BaseViewModel<LessonContract.Event, LessonContract.State, LessonContract.Effect>() {
    override fun setInitialState(): LessonContract.State {
        return LessonContract.State(
            lesson = Lesson(
                id = 1,
                title = "Cum discuti cu elevii tai despre boli psihice",
                lessonThemeTitle = "Cum discuti despre boli psihice",
                imageResourceId = R.drawable.art1,
                durationInMinutes = 7,
                remainingMinutes = 4
            ),
            started = false,
            saved = false
        )
    }
    override fun handleEvents(event: LessonContract.Event) {
        when(event) {
            is LessonContract.Event.StartContinue -> { startContinue() }
            is LessonContract.Event.Save -> { save() }
            is LessonContract.Event.Unsave -> { unsave() }
            is LessonContract.Event.NavigateUp -> { navigateUp() }
        }
    }
    private fun startContinue() {
        setState { copy(started = !viewState.value.started) }
    }

    private fun save() {
        setState { copy(saved = true) }
    }

    private fun unsave() {
        setState { copy(saved = false) }
    }

    private fun navigateUp() {
        setEffect { LessonContract.Effect.Navigation.NavigateUp }
    }
}
