package com.teacherworkout.commons.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.teacherworkout.commons.ui.base.ViewSideEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun <SideEffect : ViewSideEffect> HandleEffects(
    effects: Flow<SideEffect>,
    handleEffect: suspend (SideEffect) -> Unit
) {
    LaunchedEffect(Unit) {
        effects.onEach { handleEffect(it) }.collect()
    }
}