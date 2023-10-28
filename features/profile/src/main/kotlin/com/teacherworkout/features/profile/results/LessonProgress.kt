package com.teacherworkout.features.profile.results

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import com.teacherworkout.commons.ui.R

@Composable
fun LessonProgress(progress: Float) {
    val space1dp = dimensionResource(id = R.dimen.space_1dp)
    val space8dp = dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_8dp)
    val border1dp = dimensionResource(id = R.dimen.border_1dp)
    val corner4dp = dimensionResource(id = R.dimen.corner_4dp)
    val white = colorResource(id = com.teacherworkout.commons.ui.R.color.white)
    Box(modifier = Modifier.height(space8dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .border(width = border1dp, color = MaterialTheme.colors.primary, shape = RoundedCornerShape(corner4dp))
                .background(white),
            content = {}
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(fraction = progress)
                .fillMaxHeight()
                .padding(all = space1dp)
                .clip(RoundedCornerShape(corner4dp))
                .background(MaterialTheme.colors.primary),
            content = {}
        )
    }
}
