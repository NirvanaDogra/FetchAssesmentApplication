package com.fetchassignment.displaylist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fetchassignment.core.ui.commons.AlertDialogBoxView
import com.fetchassignment.core.ui.commons.LoadingView
import com.fetchassignment.core.ui.commons.ScreenState
import com.fetchassignment.core.ui.theme.FetchAssignmentApplicationTheme
import com.fetchassignment.displaylist.R
import com.fetchassignment.displaylist.repository.model.BaseInfo
import com.fetchassignment.displaylist.repository.model.Info
import com.fetchassignment.displaylist.repository.model.SectionHeadingInfo


@Composable
fun DisplayListScreen(
    paddingValues: PaddingValues,
    viewModel: DisplayListViewModel = hiltViewModel()
) {
    val screenState = viewModel.screenState
    Surface(
        Modifier
            .fillMaxSize()
            .padding(paddingValues),
        color = MaterialTheme.colorScheme.onBackground
    ) {

        when (screenState) {
            is ScreenState.LoadingState -> {
                LoadingView()
            }

            is ScreenState.Success -> {
                DisplayList(information = screenState.information as List<BaseInfo>)
            }

            is ScreenState.Error -> {
                AlertDialogBoxView(
                    errorMessage = screenState.error
                        ?: stringResource(R.string.something_went_wrong)
                )
            }

            else -> {
                AlertDialogBoxView(stringResource(R.string.something_went_wrong))
            }
        }
    }
}

@Composable
fun DisplayList(information: List<BaseInfo>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(information) { item ->
            DisplayListRowView(item)
        }
    }
}


@Composable
fun DisplayListRowView(item: BaseInfo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 4.dp)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        when (item) {
            is Info -> {
                ListView(item = item, modifier)
            }

            is SectionHeadingInfo -> {
                SectionHeadingView(item = item, modifier)
            }
        }
    }
}

@Composable
fun ListView(item: Info, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = item.id.toString(),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = item.listId.toString(),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(text = item.name ?: "", color = MaterialTheme.colorScheme.onPrimary)
    }
}

@Composable
fun SectionHeadingView(item: SectionHeadingInfo, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.secondary)
    ) {
        Text(
            text = "This is section ${item.section}",
            modifier.padding(16.dp),
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SectionListPreview() {
    FetchAssignmentApplicationTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            SectionHeadingView(item = SectionHeadingInfo(1))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListViewPreview() {
    FetchAssignmentApplicationTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DisplayListRowView(item = Info(1, 123, "name"))
        }
    }
}


