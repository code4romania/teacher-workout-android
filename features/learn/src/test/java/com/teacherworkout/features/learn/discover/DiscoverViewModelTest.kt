package com.teacherworkout.features.learn.discover

import androidx.compose.ui.text.input.TextFieldValue
import com.google.common.truth.Truth.assertThat
import com.teacherworkout.commons.ui.data.LessonsRepository
import com.teacherworkout.commons.ui.data.Result
import com.teacherworkout.commons.ui.model.LessonTheme
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DiscoverViewModelTest {
    @Test
    fun `will populate lesson themes`() {
        val repository: LessonsRepository = mockk()
        coEvery { repository.getAllLessonThemes() }.returns(mockResult())

        runBlocking {
            val viewModel = DiscoverViewModel(repository)
            assertThat(viewModel.viewState.value.lessonThemes).hasSize(2)
        }
    }

    @Test
    fun `will set isLoading to false`() {
        val repository: LessonsRepository = mockk()
        coEvery { repository.getAllLessonThemes() }.returns(mockEmptyResult())

        runBlocking {
            val viewModel = DiscoverViewModel(repository)
            assertThat(viewModel.viewState.value.isLoading).isFalse()
        }
    }

    @Test
    fun `will filter themes`() {
        val repository: LessonsRepository = mockk()
        coEvery { repository.getAllLessonThemes() }.returns(mockResult())

        val viewModel = runBlocking {
            DiscoverViewModel(repository)
        }

        viewModel.handleEvents(DiscoverContract.Event.SetSearchInput(TextFieldValue(text = "Some")))

        assertThat(viewModel.viewState.value.lessonThemes).hasSize(1)
    }

    @Test
    fun `will navigate to lesson theme details`() {
        val repository: LessonsRepository = mockk()
        coEvery { repository.getAllLessonThemes() }.returns(mockResult())

        val viewModel = runBlocking {
            DiscoverViewModel(repository)
        }

        viewModel.setEvent(DiscoverContract.Event.SelectLessonTheme(42))

        val effect = runBlocking {
            viewModel.effect.first()
        }

        assertThat(effect).isEqualTo(DiscoverContract.Effect.Navigation.ToLessonThemeDetails(42))
    }

    private fun mockResult(): Result<List<LessonTheme>> =
        Result.Success(listOf(mockLessonTheme(title = "Some theme"), mockLessonTheme(title = "Other theme")))

    private fun mockEmptyResult(): Result<List<LessonTheme>> =
        Result.Success(emptyList())

    private fun mockLessonTheme(title: String = "Sample"): LessonTheme =
        LessonTheme(id = 42, title = title, imageResourceId = 1)
}
