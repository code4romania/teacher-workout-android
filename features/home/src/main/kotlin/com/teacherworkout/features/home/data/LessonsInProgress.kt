package com.teacherworkout.features.home.data

import androidx.annotation.DrawableRes
import com.teacherworkout.features.learn.R

data class LessonsInProgress(
    @DrawableRes
    val imageResourceId: Int,
    val title: String,
    val category: String,
    val duration: String,
    val remainingTime: String
)

object LessonsInProgressData {
    var dummyLessonsInProgress = listOf(
        LessonsInProgress(
            R.drawable.art1,
            "Exercitii de dictie pentru copiii dislexici",
            "Cum discuți despre boli psihice",
            "5 min",
            "3 min"
        ),
        LessonsInProgress(
            R.drawable.art2,
            "7 pași pentru construirea unei lecții",
            "Interacțiunea la clasă",
            "5 min",
            "3 min"
        )
    )
}
