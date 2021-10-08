package com.teacherworkout.features.account.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.teacherworkout.features.account.BuildConfig
import com.teacherworkout.features.account.R
import com.teacherworkout.features.account.composables.ErrorText

@Composable
fun TermsAndConditions(
    isAccepted: Boolean,
    hasError: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onTosStatusChange: (Boolean) -> Unit
) {
    val space16dp = dimensionResource(id = R.dimen.space_16dp)
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isAccepted,
                enabled = enabled,
                onCheckedChange = onTosStatusChange,
                colors = CheckboxDefaults.colors(checkedColor = MaterialTheme.colors.primary)
            )
            val annotatedLinkString: AnnotatedString = buildAnnotatedString {
                // TODO find a way to handle translated strings when creating the link
                val target = "terms and conditions"
                val entireString = stringResource(id = R.string.register_tos)
                val startIndex = entireString.indexOf(target)
                val endIndex = startIndex + target.length
                append(entireString)
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.secondary,
                        textDecoration = TextDecoration.Underline
                    ), start = startIndex, end = endIndex
                )
                // add real url to properties file
                addStringAnnotation(
                    tag = "tosUrl",
                    annotation = BuildConfig.TOS_URL,
                    start = startIndex,
                    end = endIndex
                )
            }
            val uriHandler = LocalUriHandler.current
            ClickableText(
                modifier = Modifier.padding(start = space16dp),
                text = annotatedLinkString,
                onClick = {
                    annotatedLinkString
                        .getStringAnnotations("tosUrl", it, it)
                        .firstOrNull()?.let { stringAnnotation ->
                            uriHandler.openUri(stringAnnotation.item)
                        }
                }
            )
        }
        if (hasError) {
            ErrorText(errorTextId = R.string.register_tos_error)
        }
    }
}
