package com.teacherworkout.features.learn.lesson

import com.teacherworkout.commons.ui.base.BaseViewModel

//TODO: properly implement the LessonViewModel
class LessonViewModel(
    private val lessonId: String
): BaseViewModel<LessonContract.Event, LessonContract.State, LessonContract.Effect>() {
    override fun setInitialState(): LessonContract.State {
        return LessonContract.State(
            lessonName = "Cum discuti cu elevii tai despre boli psihice",
            lessonThemeName = "Cum discuti despre boli psihice",
            duration = "7 min",
            started = false,
            saved = false
        )
    }

    override fun handleEvents(event: LessonContract.Event) {
        when(event) {
            is LessonContract.Event.StartContinue -> { startContinue() }
            is LessonContract.Event.Save -> { save() }
            is LessonContract.Event.Unsave -> { unsave() }
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
}
