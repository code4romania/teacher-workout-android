package com.teacherworkout.features.discover

import androidx.compose.ui.text.input.TextFieldValue
import com.google.common.truth.Truth.assertThat
import com.teacherworkout.features.discover.landing.Contract
import com.teacherworkout.features.discover.landing.ViewModel
import com.teacherworkout.features.discover.landing.Theme
import com.teacherworkout.features.discover.landing.ThemesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class DiscoverViewModelTest {
    @Test
    fun `will populate lesson themes`() {
        val repository: ThemesRepository = mockk()
        coEvery { repository.all() }.returns(mockResult())

        runBlocking {
            val viewModel = ViewModel(repository)
            assertThat(viewModel.viewState.value.themes).hasSize(2)
        }
    }

    @Test
    fun `will set isLoading to false`() {
        val repository: ThemesRepository = mockk()
        coEvery { repository.all() }.returns(mockEmptyResult())

        runBlocking {
            val viewModel = ViewModel(repository)
            assertThat(viewModel.viewState.value.isLoading).isFalse()
        }
    }

    @Test
    fun `will filter themes`() {
        val repository: ThemesRepository = mockk()
        coEvery { repository.all() }.returns(mockResult())

        val viewModel = runBlocking {
            ViewModel(repository)
        }

        viewModel.handleEvents(Contract.Event.SetSearchInput(TextFieldValue(text = "Some")))

        assertThat(viewModel.viewState.value.themes).hasSize(1)
    }

    @Test
    fun `will navigate to lesson theme details`() {
        val repository: ThemesRepository = mockk()
        coEvery { repository.all() }.returns(mockResult())

        val viewModel = runBlocking {
            ViewModel(repository)
        }

        viewModel.setEvent(Contract.Event.SelectLessonTheme("42"))

        val effect = runBlocking {
            viewModel.effect.first()
        }

        assertThat(effect).isEqualTo(Contract.Effect.Navigation.ToLessonThemeDetails("42"))
    }

    private fun mockResult(): Result<List<Theme>> =
        Result.success(listOf(mockLessonTheme(title = "Some theme"), mockLessonTheme(title = "Other theme")))

    private fun mockEmptyResult(): Result<List<Theme>> =
        Result.success(emptyList())

    private fun mockLessonTheme(title: String = "Sample"): Theme =
        Theme(id = "42", title = title)
}
