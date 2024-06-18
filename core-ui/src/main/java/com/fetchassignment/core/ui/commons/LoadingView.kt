package com.fetchassignment.core.ui.commons

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.padding(24.dp),
        trackColor = MaterialTheme.colorScheme.onPrimary)
}

@Preview()
@Composable
fun LoadingScreenPreview() {
    LoadingView()
}