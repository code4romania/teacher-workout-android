package com.teacherworkout.features.account.auth

import com.google.common.truth.Truth.assertThat
import com.teacherworkout.features.account.data.AccountTestData.VALID_EMAIL
import com.teacherworkout.features.account.data.AccountTestData.VALID_PASSWORD
import org.junit.Before
import org.junit.Test

class AuthViewModelTest {

    private lateinit var viewModel: AuthViewModel

    @Before
    fun setUp() {
        viewModel = AuthViewModel()
    }

    @Test
    fun `initialState is expected state`() {
        assertThat(viewModel.setInitialState()).isEqualTo(AuthContract.State())
    }

    @Test
    fun `handleEvents will handle SetEmail`() {
        viewModel.handleEvents(AuthContract.Event.SetEmail(VALID_EMAIL))
        assertThat(viewModel.viewState.value.email).isEqualTo(VALID_EMAIL)
    }

    @Test
    fun `handleEvents will handle SetPassword`() {
        viewModel.handleEvents(AuthContract.Event.SetPassword(VALID_PASSWORD))
        assertThat(viewModel.viewState.value.password).isEqualTo(VALID_PASSWORD)
    }
}
