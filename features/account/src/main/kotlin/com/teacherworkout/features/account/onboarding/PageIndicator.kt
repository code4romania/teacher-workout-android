package com.teacherworkout.features.account.onboarding

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.features.account.R
import kotlin.math.min

// set 30% of the available space as margin between the circles, should probably calculate this
const val PERCENT = 0.3F

@Composable
fun PageIndicator(pageCount: Int, selected: Int, modifier: Modifier = Modifier) {
    val primaryColor = MaterialTheme.colors.primary
    Canvas(modifier = modifier) {
        val spaceBetween = size.width * PERCENT / pageCount
        val indicatorRadius = min((size.width - spaceBetween * (2 + pageCount - 1)) / pageCount, size.height) / 2
        repeat(pageCount) { index ->
            val calculatedCenter = Offset(
                x = spaceBetween + indicatorRadius + index * 2 * indicatorRadius + spaceBetween * index,
                y = center.y
            )
            val calculatedStyle = if (index == selected) Fill else Stroke()
            drawCircle(primaryColor, radius = indicatorRadius, center = calculatedCenter, style = calculatedStyle)
        }
    }
}

@Preview
@Composable
fun PageIndicatorPreview() {
    PageIndicator(
        pageCount = 3, selected = 0, modifier = Modifier
            .height(dimensionResource(id = R.dimen.space_32dp))
            .width(dimensionResource(id = R.dimen.space_64dp))
    )
}
