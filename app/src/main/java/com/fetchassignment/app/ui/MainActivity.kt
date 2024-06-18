@file:OptIn(ExperimentalMaterial3Api::class)

package com.fetchassignment.app.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fetchassignment.app.R
import com.fetchassignment.core.ui.theme.FetchAssignmentApplicationTheme
import com.fetchassignment.displaylist.ui.DisplayListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchAssignmentApplicationTheme {
                MainActivityScreen()
            }
        }
    }
}

@Composable
fun MainActivityScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopBarView() }
    ) { innerPadding ->
        DisplayListScreen(innerPadding)
    }
}


@Composable
fun TopBarView() {
    Surface(
        modifier = Modifier.fillMaxWidth().height(64.dp),
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Text(
            text = stringResource(R.string.fetch_assignment),
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
            textAlign = TextAlign.Left,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FetchAssignmentApplicationTheme {
        TopBarView()
    }
}