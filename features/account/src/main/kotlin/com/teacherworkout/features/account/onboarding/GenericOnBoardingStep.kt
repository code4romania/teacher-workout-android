package com.teacherworkout.features.account.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.features.account.R
import com.teacherworkout.commons.ui.R as CommonRes


/**
 * Because the mockups for the 3 pages are almost the same, currently there is only this composable to describe the
 * content for all onboarding pages. If the real content will be different then different pages should be implemented.
 */
@Composable
fun GenericOnBoardingStep(
    @DrawableRes onboardingImageId: Int,
    @StringRes onboardingTitle: Int,
    @StringRes onboardingText: Int,
    modifier: Modifier = Modifier
) {
    val space8dp = dimensionResource(id = CommonRes.dimen.space_8dp)
    val space16dp = dimensionResource(id = CommonRes.dimen.space_16dp)
    Column(
        modifier = modifier
            .background(colorResource(id = CommonRes.color.white))
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.5f)
                .background(colorResource(id = CommonRes.color.landing_background))
        ) {
            Image(
                painter = painterResource(id = onboardingImageId),
                contentDescription = "TODO - when creating the actual onboarding",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(fraction = 0.9f)
                    .fillMaxHeight(fraction = 0.5f)
            )
        }
        Spacer(modifier = Modifier.height(space16dp))
        Text(
            text = stringResource(id = onboardingTitle),
            style = TextStyle(
                color = MaterialTheme.colors.primary,
                fontSize = MaterialTheme.typography.h5.fontSize,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = space16dp)
        )
        Spacer(modifier = Modifier.height(space8dp))
        Text(
            text = stringResource(id = onboardingText),
            style = TextStyle(
                color = colorResource(id = CommonRes.color.onboarding_text_description),
                fontSize = MaterialTheme.typography.body2.fontSize,
            ),
            modifier = Modifier.padding(horizontal = space16dp)
        )
    }
}

@Preview
@Composable
fun GenericOnBoardingStepPreview() {
    GenericOnBoardingStep(
        onboardingImageId = CommonRes.drawable.onboarding_0_placeholder,
        onboardingTitle = R.string.onboarding_0_title,
        onboardingText = R.string.onboarding_0_description,
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.onboarding_width))
            .height(dimensionResource(id = R.dimen.onboarding_height))
    )
}
