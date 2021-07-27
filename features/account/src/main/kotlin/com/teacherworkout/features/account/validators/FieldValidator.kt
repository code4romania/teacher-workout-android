package com.teacherworkout.features.account.validators

interface FieldValidator<out T> {

    fun validate(input: String): T
}
