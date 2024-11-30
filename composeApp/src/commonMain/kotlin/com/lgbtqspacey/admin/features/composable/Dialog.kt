package com.lgbtqspacey.admin.features.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.constraintlayout.compose.ConstraintLayout
import com.lgbtqspacey.admin.helpers.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
    titleText: String,
    subtitleText: String,
    dismissBtnText: String,
    confirmBtnText: String,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            val (
                title,
                subtitle,
                dismissBtn,
                confirmBtn,
            ) = createRefs()

            Text(
                text = titleText,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(title) {
                    end.linkTo(parent.end, Dimensions.SIZE_16.dp())
                    start.linkTo(parent.start, Dimensions.SIZE_16.dp())
                    top.linkTo(parent.top, Dimensions.SIZE_16.dp())
                }
            )

            Text(
                text = subtitleText,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.constrainAs(subtitle) {
                    end.linkTo(parent.end, Dimensions.SIZE_16.dp())
                    start.linkTo(parent.start, Dimensions.SIZE_16.dp())
                    top.linkTo(title.bottom, Dimensions.SIZE_8.dp())
                }
            )

            OutlinedButton(
                onClick = { onDismiss() },
                modifier = Modifier.constrainAs(dismissBtn) {
                    top.linkTo(subtitle.bottom, Dimensions.SIZE_8.dp())
                    start.linkTo(parent.start, Dimensions.SIZE_16.dp())
                    end.linkTo(confirmBtn.start, Dimensions.SIZE_8.dp())
                    bottom.linkTo(parent.bottom, Dimensions.SIZE_16.dp())
                }
            ) {
                Text(dismissBtnText)
            }

            Button(onClick = { onConfirm() },
                modifier = Modifier.constrainAs(confirmBtn) {
                    top.linkTo(subtitle.bottom, Dimensions.SIZE_8.dp())
                    start.linkTo(dismissBtn.end, Dimensions.SIZE_8.dp())
                    end.linkTo(parent.end, Dimensions.SIZE_16.dp())
                    bottom.linkTo(parent.bottom, Dimensions.SIZE_16.dp())
                }
            ) {
                Text(confirmBtnText)
            }
        }
    }
}
