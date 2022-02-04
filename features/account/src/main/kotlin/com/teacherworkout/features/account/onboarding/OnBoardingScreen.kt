package com.teacherworkout.features.account.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.teacherworkout.features.account.R
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(onNavigateToMain: () -> Unit) {
    val space16dp = dimensionResource(R.dimen.space_16dp)
    val space24dp = dimensionResource(R.dimen.space_24dp)
    val pageCount = 3
    val pagerState = rememberPagerState()
    val localScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        HorizontalPager(
            count = pageCount,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            val imageId = when (page) {
                0 -> R.drawable.onboarding_0_placeholder
                1 -> R.drawable.onboarding_1_placeholder
                2 -> R.drawable.onboarding_2_placeholder
                else -> error("Unexpected onboarding page requested: ${pagerState.currentPage}")
            }
            // until creating the actual onboarding, use the same title/description
            GenericOnBoardingStep(
                onboardingImageId = imageId,
                onboardingTitle = R.string.onboarding_0_title,
                onboardingText = R.string.onboarding_0_description
            )
        }
        Box(
            modifier = Modifier
                .padding(all = space16dp)
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TextButton(
                    onClick = onNavigateToMain
                ) {
                    Text(
                        stringResource(R.string.onboarding_btn_skip),
                        color = Color.Gray,
                        style = TextStyle(fontWeight = FontWeight.Light),
                    )
                }
                val hasMoreSteps = pagerState.currentPage != pagerState.pageCount - 1
                NextStepButton(
                    hasMoreSteps = hasMoreSteps,
                    onNext = {
                        if (hasMoreSteps) {
                            localScope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        } else {
                            onNavigateToMain()
                        }
                    }
                )
            }
            PageIndicator(
                pageCount = pageCount, pagerState.currentPage,
                modifier = Modifier
                    .size(width = space24dp, height = space16dp)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun OnBoardingScreenPreview() {
    OnBoardingScreen {}
}
