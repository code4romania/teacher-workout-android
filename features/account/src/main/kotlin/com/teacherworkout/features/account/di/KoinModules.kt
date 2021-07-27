package com.teacherworkout.features.account.di

import com.teacherworkout.features.account.auth.AuthViewModel
import com.teacherworkout.features.account.register.RegistrationViewModel
import com.teacherworkout.features.account.reset.ResetPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val accountModule = module {
    viewModel { AuthViewModel() }
    viewModel { RegistrationViewModel() }
    viewModel { ResetPasswordViewModel() }
}