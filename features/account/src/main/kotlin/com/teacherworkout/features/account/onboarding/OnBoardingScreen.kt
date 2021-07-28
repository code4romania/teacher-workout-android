package com.teacherworkout.features.account.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.teacherworkout.android.theming.TeacherWorkoutTheme
import com.teacherworkout.features.account.R

@ExperimentalPagerApi
@Composable
fun OnBoardingScreen() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Column(modifier = Modifier.background(Color(236, 253, 245))) {
            val pagerState = rememberPagerState(
                pageCount = 3,
                // We increase the offscreen limit, to allow pre-loading of images
                initialOffscreenLimit = 2,
            )

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.50f),
            ) {
                Image(
                    painter = painterResource(id = onBoardingImages()[currentPage]),
                    "Onboarding",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .background(Color(236, 253, 245))
                        .fillMaxSize(),
                    contentScale = ContentScale.None
                )
            }

            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
                    .background(Color(236, 253, 245)),
                activeColor = Color(47, 128, 237),
            )
        }

        Column(
            modifier = Modifier
                .background(Color(255, 255, 255))
                .fillMaxSize()
        ) {
            Text(
                stringResource(id = R.string.onboarding_title),
                fontSize = 32.sp,
                color = Color(8, 145, 178),
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .padding(top = 32.dp),
                style = MaterialTheme.typography.h1,
            )
            Spacer(Modifier.height(22.dp))
            Text(
                stringResource(id = R.string.onboarding_body),
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.body1,
                fontSize = 16.sp
            )
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    border = BorderStroke(1.dp, Color(8, 145, 178)),
                    modifier = Modifier
                        .padding(8.dp)
                        .padding(end = 32.dp)
                        .padding(bottom = 66.dp)
                        .align(Alignment.End),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    )

                ) {
                    Text(
                        "NEXT", color = Color(8, 145, 178),
                        fontSize = 15.sp,
                        style = MaterialTheme.typography.body1
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.padding(start = 4.dp),
                        tint = Color(8, 145, 178)
                    )
                }
            }
        }

    }
    Column(Modifier.fillMaxSize()) {
        TextButton(
            onClick = { /*TODO*/ },
            Modifier
                .align(Alignment.End)
                .padding(end = 32.dp)
        ) {
            Text(
                text = "SKIP",
                color = Color.Black,
                fontSize = 15.sp,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

fun onBoardingImages() = mutableListOf(
    R.drawable.ic_onboarding_1,
    R.drawable.ic_onboarding_2,
    R.drawable.ic_onboarding_3
)

@ExperimentalPagerApi
@Preview
@Composable
fun Preview() {
    TeacherWorkoutTheme {
        Surface {
            OnBoardingScreen()
        }
    }
}