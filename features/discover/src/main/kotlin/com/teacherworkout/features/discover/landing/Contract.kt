package com.teacherworkout.features.discover.landing

import androidx.compose.ui.text.input.TextFieldValue
import com.teacherworkout.commons.ui.base.ViewEvent
import com.teacherworkout.commons.ui.base.ViewSideEffect
import com.teacherworkout.commons.ui.base.ViewState

class Contract {
    sealed class Event : ViewEvent {
        data class SetSearchInput(val searchInput: TextFieldValue) : Event()
        data class SelectLessonTheme(val lessonThemeId: String): Event()
    }

    data class State(
        val searchInput: TextFieldValue = TextFieldValue(),
        val themes: List<Theme> = emptyList(),
        val isLoading: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation: Effect() {
            data class ToLessonThemeDetails(val lessonThemeId: String): Navigation()
        }
    }
}
