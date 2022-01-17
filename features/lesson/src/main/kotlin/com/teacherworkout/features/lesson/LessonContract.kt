package com.teacherworkout.features.lesson

import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.core.fragment.Lesson

class LessonContract {
    sealed class Event : ViewEvent {
        object NavigateUp : Event()
        object StartContinue : Event()
        object Save : Event()
        object Unsave : Event()
    }

    data class State(
        val lesson: Lesson? = null
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation: Effect() {
            object NavigateUp: Navigation()
        }
    }
}
