package com.teacherworkout.features.profile.landing

import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.teacherworkout.features.profile.R
import com.teacherworkout.features.profile.results.ResultsTabContent
import com.teacherworkout.features.profile.settings.SettingsTabContent
import com.teacherworkout.commons.ui.R as CommonRes


@Composable
fun Screen() {
    var tab by remember { mutableStateOf(0) }
    val space16dp = dimensionResource(id = CommonRes.dimen.space_16dp)
    val minTabHeight = dimensionResource(id = CommonRes.dimen.min_tab_height)

    var profilePictureUri by remember { mutableStateOf<Uri?>(null) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(top = space16dp)
            .fillMaxWidth()
    ) {
        ProfilePicture(pictureUri = profilePictureUri)
        Spacer(Modifier.height(space16dp))
        ExperienceLevelText(level = 1024)
        Spacer(Modifier.height(space16dp))
        TabRow(
            selectedTabIndex = tab,
            backgroundColor = Color.White,
            contentColor = MaterialTheme.colors.primary
        ) {
            ProfileTab(
                textId = R.string.tab_label_results, isSelected = tab == 0, onTabSelected = { tab = 0 },
                modifier = Modifier.heightIn(min = minTabHeight)
            )
            ProfileTab(
                textId = R.string.tab_label_settings, isSelected = tab == 1, onTabSelected = { tab = 1 },
                modifier = Modifier.heightIn(min = minTabHeight)
            )
        }
        when (tab) {
            0 -> ResultsTabContent(listOf("Category 1", "Category 2", "Category 3", "Category 4", "Category 5"))
            1 -> SettingsTabContent(onNewPicture = { uri -> profilePictureUri = uri })
            else -> error("Unexpected tab requested: $tab")
        }
    }
}
