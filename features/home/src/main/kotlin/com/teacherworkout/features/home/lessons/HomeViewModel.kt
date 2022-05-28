package com.teacherworkout.features.home.lessons

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.teacherworkout.commons.ui.base.BaseViewModel
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.data.Result
import com.teacherworkout.commons.ui.model.Lesson
import com.teacherworkout.commons.ui.model.LessonTheme
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(
    private val lessonsRepository: LessonsRepository
) : BaseViewModel<HomeContract.Event, HomeContract.State, HomeContract.Effect>() {

    private var allLessons: List<Lesson> = emptyList()
    private var allLessonThemes: List<LessonTheme> = emptyList()

    override fun setInitialState(): HomeContract.State = HomeContract.State()

    override fun handleEvents(event: HomeContract.Event) {
        when (event) {
            is HomeContract.Event.SetSearchInput -> setSearchInput(event.searchInput)
            is HomeContract.Event.SelectLessonTheme -> selectLessonTheme(event.lessonThemeId)
            is HomeContract.Event.SelectLesson -> selectLesson(event.lessonId)
        }
    }

    init {
        refreshData()
    }

    private fun refreshData() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            lateinit var lessonThemeResult: Result<List<LessonTheme>>
            lateinit var lessonsResult: Result<List<Lesson>>
            awaitAll(
                async {
                    lessonThemeResult = lessonsRepository.getAllLessonThemes()
                },
                async {
                    lessonsResult = lessonsRepository.getAllLessons()
                }
            )
            setState { copy(isLoading = false) }
            if(lessonsResult is Result.Success && lessonThemeResult is Result.Success) {
                allLessons = (lessonsResult as Result.Success<List<Lesson>>).data
                allLessonThemes = (lessonThemeResult as Result.Success<List<LessonTheme>>).data
                setState {
                    copy(
                        searchInput = TextFieldValue(),
                        lessons = allLessons,
                        lessonThemes = allLessonThemes
                    )
                }
            }
        }
    }

    private fun setSearchInput(searchInput: TextFieldValue) {
        val prevSearchInput = viewState.value.searchInput
        setState { copy(searchInput = searchInput) }
        if (prevSearchInput.text != searchInput.text) {
            applyRandomFilter(searchInput.text)
        }
    }

    private fun selectLessonTheme(lessonThemeId: String) {
        setEffect {
            HomeContract.Effect.Navigation.ToLessonThemeDetails(lessonThemeId)
        }
    }

    private fun selectLesson(lessonId: Long) {
        setEffect {
            HomeContract.Effect.Navigation.ToLessonDetails(lessonId)
        }
    }

    private fun applyRandomFilter(searchInput: String) {
        if (searchInput.isBlank()) {
            setState { copy(lessonThemes = allLessonThemes) }
        } else {
            val randomNumberOfLessonThemes = Random.nextInt(1, allLessonThemes.size)
            val someLessonThemes = mutableListOf<LessonTheme>()
            repeat(randomNumberOfLessonThemes) {
                someLessonThemes.add(allLessonThemes[Random.nextInt(0, allLessonThemes.size)])
            }
            setState { copy(lessonThemes = someLessonThemes) }
        }
    }
}
