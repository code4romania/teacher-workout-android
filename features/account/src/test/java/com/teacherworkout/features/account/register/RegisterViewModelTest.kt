package com.teacherworkout.features.account.register

import com.google.common.truth.Truth.assertThat
import com.teacherworkout.features.account.data.AccountTestData.EMPTY
import com.teacherworkout.features.account.data.AccountTestData.INVALID_EMAIL
import com.teacherworkout.features.account.data.AccountTestData.INVALID_PASSWORD_NO_DIGIT
import com.teacherworkout.features.account.data.AccountTestData.INVALID_PASSWORD_NO_LOWERCASE
import com.teacherworkout.features.account.data.AccountTestData.INVALID_PASSWORD_NO_SPECIAL_CHAR
import com.teacherworkout.features.account.data.AccountTestData.INVALID_PASSWORD_NO_UPPERCASE
import com.teacherworkout.features.account.data.AccountTestData.VALID_EMAIL
import com.teacherworkout.features.account.data.AccountTestData.VALID_PASSWORD
import com.teacherworkout.features.account.validators.EmailValidationStatus
import com.teacherworkout.features.account.validators.PasswordValidationStatus
import org.junit.Before
import org.junit.Test

class RegisterViewModelTest {

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        viewModel = RegisterViewModel()
    }


    @Test
    fun `initialState is expected state`() {
        assertThat(viewModel.setInitialState()).isEqualTo(RegisterContract.State())
    }

    @Test
    fun `viewState has expected value with SetEmail(validEmail)`() {
        viewModel.handleEvents(RegisterContract.Event.SetEmail(VALID_EMAIL))
        with(viewModel.viewState.value) {
            assertThat(email).isEqualTo(VALID_EMAIL)
            assertThat(emailStatus).isEqualTo(EmailValidationStatus.Valid)
        }
    }

    @Test
    fun `viewState has expected value with SetEmail(emptyEmail)`() {
        viewModel.handleEvents(RegisterContract.Event.SetEmail(EMPTY))
        with(viewModel.viewState.value) {
            assertThat(email).isEqualTo(EMPTY)
            assertThat(emailStatus).isEqualTo(EmailValidationStatus.Invalid)
        }
    }

    @Test
    fun `viewState has expected value with SetEmail(invalidEmail)`() {
        viewModel.handleEvents(RegisterContract.Event.SetEmail(INVALID_EMAIL))
        with(viewModel.viewState.value) {
            assertThat(email).isEqualTo(INVALID_EMAIL)
            assertThat(emailStatus).isEqualTo(EmailValidationStatus.Invalid)
        }
    }

    @Test
    fun `viewState has expected value with SetPassword(validPassword)`() {
        viewModel.handleEvents(RegisterContract.Event.SetPassword(VALID_PASSWORD))
        with(viewModel.viewState.value) {
            assertThat(password).isEqualTo(VALID_PASSWORD)
            assertThat(passwordStatus).isEqualTo(PasswordValidationStatus.Valid)
        }
    }

    @Test
    fun `viewState has expected value with SetPassword(tooShortPassword)`() {
        viewModel.handleEvents(RegisterContract.Event.SetPassword(EMPTY))
        with(viewModel.viewState.value) {
            assertThat(password).isEqualTo(EMPTY)
            assertThat(passwordStatus).isEqualTo(PasswordValidationStatus.TooShort)
        }
    }

    @Test
    fun `viewState has expected value with SetPassword(noUppercase)`() {
        viewModel.handleEvents(RegisterContract.Event.SetPassword(INVALID_PASSWORD_NO_UPPERCASE))
        with(viewModel.viewState.value) {
            assertThat(password).isEqualTo(INVALID_PASSWORD_NO_UPPERCASE)
            assertThat(passwordStatus).isEqualTo(PasswordValidationStatus.NoUppercase)
        }
    }

    @Test
    fun `viewState has expected value with SetPassword(noLowercase)`() {
        viewModel.handleEvents(RegisterContract.Event.SetPassword(INVALID_PASSWORD_NO_LOWERCASE))
        with(viewModel.viewState.value) {
            assertThat(password).isEqualTo(INVALID_PASSWORD_NO_LOWERCASE)
            assertThat(passwordStatus).isEqualTo(PasswordValidationStatus.NoLowercase)
        }
    }

    @Test
    fun `viewState has expected value with SetPassword(noDigit)`() {
        viewModel.handleEvents(RegisterContract.Event.SetPassword(INVALID_PASSWORD_NO_DIGIT))
        with(viewModel.viewState.value) {
            assertThat(password).isEqualTo(INVALID_PASSWORD_NO_DIGIT)
            assertThat(passwordStatus).isEqualTo(PasswordValidationStatus.NoDigit)
        }
    }

    @Test
    fun `viewState has expected value with SetPassword(noSpecialChar)`() {
        viewModel.handleEvents(RegisterContract.Event.SetPassword(INVALID_PASSWORD_NO_SPECIAL_CHAR))
        with(viewModel.viewState.value) {
            assertThat(password).isEqualTo(INVALID_PASSWORD_NO_SPECIAL_CHAR)
            assertThat(passwordStatus).isEqualTo(PasswordValidationStatus.NoSpecialChar)
        }
    }
}
