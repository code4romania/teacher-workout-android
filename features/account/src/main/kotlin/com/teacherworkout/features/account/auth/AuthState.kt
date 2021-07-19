package com.teacherworkout.features.account.auth

data class AuthState(
    val email: String = "",
    val password: String = "",
    val authOutcome: AuthOutcome = NotInitiated
)
