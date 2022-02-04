package com.teacherworkout.features.home.lessons

import androidx.compose.ui.text.input.TextFieldValue
import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState
import com.teacherworkout.core.fragment.LessonStatus
import com.teacherworkout.core.fragment.LessonTheme

class HomeContract {
    sealed class Event : ViewEvent {
        data class SetSearchInput(val searchInput: TextFieldValue) : Event()
        data class SelectLessonTheme(val lessonThemeId: String) : Event()
        data class SelectLesson(val lessonId: String): Event()
    }

    data class State(
        val searchInput: TextFieldValue = TextFieldValue(),
        val lessonStatuses: List<LessonStatus> = emptyList(),
        val lessonThemes: List<LessonTheme> = emptyList(),
        val isLoading: Boolean = true
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToLessonDetails(val lessonId: String) : Navigation()
            data class ToLessonThemeDetails(val lessonThemeId: String) : Navigation()
        }
    }
}
