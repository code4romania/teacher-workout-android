package com.teacherworkout.features.home.data

import androidx.annotation.DrawableRes
import com.teacherworkout.features.learn.R

data class LessonsNewAdded(
    @DrawableRes
    val imageResourceId: Int,
    val title: String,
    val category: String,
    val duration: String,
    val remainingTime: String
)

object LessonsNewAddedData {
    var dummyLessonsNewAdded = listOf(
        LessonsNewAdded(
            R.drawable.art1,
            "Metode corecte de oferire de feedback ",
            "Cum discuți despre boli psihice",
            "7 min",
            "3min"
        ),
        LessonsNewAdded(
            R.drawable.art2,
            "Cum discuți despre bullying - Clasa a 3-a",
            "Interacțiunea la clasă",
            "5 min",
            "3min"
        ),
        LessonsNewAdded(
            R.drawable.art3,
            "Cum negociezi cu un copil dificil",
            "Interacțiunea la clasă",
            "5 min",
            "3min"
        )
    )
}
