package com.teacherworkout.features.home.landing

import androidx.compose.ui.text.input.TextFieldValue
import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.commons.ui.model.LessonTheme

class LandingContract {
    sealed class Event : ViewEvent {
        data class SetSearchInput(val searchInput: TextFieldValue) : Event()
        data class SelectLessonTheme(val lessonThemeId: String) : Event()
        data class SelectLesson(val lessonId: Long): Event()
    }

    data class State(
        val searchInput: TextFieldValue = TextFieldValue(),
        val lessons: List<Lesson> = emptyList(),
        val lessonThemes: List<LessonTheme> = emptyList(),
        val isLoading: Boolean = true
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToLessonDetails(val lessonId: Long) : Navigation()
            data class ToLessonThemeDetails(val lessonThemeId: String) : Navigation()
        }
    }
}
