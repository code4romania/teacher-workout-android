package com.teacherworkout.features.account.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.teacherworkout.features.account.R

@Composable
fun NextStepButton(hasMoreSteps: Boolean, onNext: () -> Unit, modifier: Modifier = Modifier) {
    val space1dp = dimensionResource(com.teacherworkout.commons.ui.R.dimen.space_1dp)
    val space4dp = dimensionResource(com.teacherworkout.commons.ui.R.dimen.space_4dp)
    val space24dp = dimensionResource(com.teacherworkout.commons.ui.R.dimen.space_24dp)
    val labelText = nextLabelText(hasMoreSteps)
    OutlinedButton(
        onClick = onNext,
        modifier = modifier,
        border = BorderStroke(space1dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(space24dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.height(IntrinsicSize.Min)
        ) {
            Text(labelText)
            Spacer(modifier = Modifier.width(space4dp))
            Icon(
                modifier = Modifier.height(with(LocalDensity.current) {
                    MaterialTheme.typography.button.fontSize.toDp()
                }),
                painter = painterResource(id = com.teacherworkout.commons.ui.R.drawable.ic_forward),
                contentDescription = stringResource(id = R.string.onboarding_cd_forward)
            )
        }
    }
}

@Composable
private fun nextLabelText(hasMoreSteps: Boolean) =
    stringResource(id = if (hasMoreSteps) R.string.onboarding_btn_next_step else R.string.onboarding_btn_next_finish)

@Preview
@Composable
fun NextStepButtonPreview() {
    NextStepButton(
        hasMoreSteps = true, onNext = { /*TODO*/ }, modifier = Modifier
            .width(dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_128dp))
            .height(dimensionResource(id = com.teacherworkout.commons.ui.R.dimen.space_64dp))
    )
}
