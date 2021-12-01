package com.teacherworkout.features.learn.lesson

import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState

class LessonContract {
    sealed class Event : ViewEvent {
        class StartContinue() : Event()
        class Save() : Event()
        class Unsave(): Event()
    }

    data class State(
        val lessonName: String = "",
        val lessonThemeName: String = "",
        val duration: String = "",
        val started: Boolean = false,
        val saved: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {

    }
}
