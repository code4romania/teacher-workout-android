package com.teacherworkout.features.account.auth

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AuthViewModelTest {
    @Test
    fun `setInitialState will set the initial state`() {
        val viewModel = AuthViewModel()

        assertThat(viewModel.setInitialState()).isEqualTo(AuthContract.State())
    }

    @Test
    fun `handleEvents will handle SetEmail`() {
        val viewModel = AuthViewModel()

        viewModel.handleEvents(AuthContract.Event.SetEmail("ion@code4.ro"))

        assertThat(viewModel.viewState.value.email).isEqualTo("ion@xxx.ro")
    }

    @Test
    fun `handleEvents will handle SetPassword`() {
        val viewModel = AuthViewModel()

        viewModel.handleEvents(AuthContract.Event.SetPassword("horsestampler"))

        assertThat(viewModel.viewState.value.password).isEqualTo("horsestampler")
    }
}
