package com.example.lesson

import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.commons.ui.model.Lesson

class LessonContract {
    sealed class Event : ViewEvent {
        class StartContinue() : Event()
        class Save() : Event()
        class Unsave(): Event()
    }

    data class State(
        val lesson: Lesson,
        val started: Boolean = false,
        val saved: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {

    }
}
