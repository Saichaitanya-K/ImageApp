package com.example.imageapp.dialogBox

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.imageapp.R

/**
 * Composable having dialog content
 *
 * @param textMessage Message to be shown in the dialog
 * @param dismissOnClickOutside boolean whether the dialog should be
 * dismissed on click outside when shown or not
 * @param buttonText text to be shown in button
 * @param onButtonClicked invokes when the button is clicked
 * @param onDismissRequest When dialog is dismissed by touching outside
 */
@Composable
fun DialogContent(
    textMessage: String,
    dismissOnClickOutside: Boolean = true,
    @StringRes buttonText: Int = R.string.ok,
    onButtonClicked: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismissRequest.invoke() },
        properties = DialogProperties(
            dismissOnClickOutside = dismissOnClickOutside
        )
    ) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(10.dp)),
            color = Color.Transparent
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
            ) {
                // Message
                Text(
                    text = textMessage,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    style = MaterialTheme.typography.body1,
                    lineHeight = 22.sp
                )
                // Shows the line between the text and the button
                Divider(
                    color = Color.Gray.copy(0.6f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
                // Button
                TextButton(
                    onClick = { onButtonClicked.invoke() },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = buttonText),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 10.dp
                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        color = Color.Blue,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
        }
    }
}