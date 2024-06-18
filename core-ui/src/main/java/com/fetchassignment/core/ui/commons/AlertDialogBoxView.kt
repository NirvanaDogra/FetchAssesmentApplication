package com.fetchassignment.core.ui.commons

import android.app.Activity
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fetchassignment.core.ui.R

@Composable
fun AlertDialogBoxView(errorMessage: String) {
    val application = LocalContext.current as? Activity
    AlertDialog(
        title = { Text(stringResource(R.string.error)) },
        text = { Text(errorMessage) },
        onDismissRequest = { application?.finish() },
        confirmButton = {
            Button(onClick = { application?.finish() }) {
                Text(text = stringResource(R.string.ok))
            }
        })
}


@Preview
@Composable
fun AlertDialogBoxViewPreview() {
    AlertDialogBoxView("Something went wrong !!")
}