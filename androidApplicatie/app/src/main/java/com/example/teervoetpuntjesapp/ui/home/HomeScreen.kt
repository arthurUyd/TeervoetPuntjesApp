package com.example.teervoetpuntjesapp.ui.theme.pages

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.ui.home.BadgeUiState
import com.example.teervoetpuntjesapp.ui.home.HomeScreenState
import com.example.teervoetpuntjesapp.ui.home.HomeScreenViewModel
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.Home
}

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
    modifier: Modifier = Modifier,
    badgePress: (Int) -> Unit,
) {
    val uiState: HomeScreenState by homeScreenViewModel.uiState.collectAsState()

    if (uiState.isError) {
        Text(text = stringResource(id = R.string.error_text))
        homeScreenViewModel.onErrorConsumed()
    }

    Box(modifier = modifier) {
        when (uiState.badges) {
            is BadgeUiState.Loading -> Text(stringResource(id = R.string.loading_text))
            is BadgeUiState.Error -> Text(text = stringResource(id = R.string.error_text))
            is BadgeUiState.Success -> BadgeLijst(uiState = uiState.badges, modifier = modifier, onPress = { id -> badgePress(id) })

        }
    }
}

@Composable
fun BadgeLijst(
    uiState: BadgeUiState,
    modifier: Modifier = Modifier,
    onPress: (Int) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.padding(all = 14.dp).fillMaxSize(),
        state = lazyListState,
    ) {
        when (uiState) {
            BadgeUiState.Error -> {
                errorText(R.string.error_text)
            }
            BadgeUiState.Loading -> {
                item {
                    LoadingIndicator()
                }
            }
            is BadgeUiState.Success -> {
                items(uiState.badges) {
                    BadgeCard(badge = it, modifier = Modifier.padding(6.dp), onClick = {
                        onPress(it.id)
                    })
                }
            }
        }
    }
}

@Composable
fun BadgeCard(
    badge: Badge,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(255.dp).background(MaterialTheme.colorScheme.primaryContainer).padding(8.dp)
                .clickable { onClick() },
        ) {
            AsyncImage(
                model = badge.image_url,
                contentDescription = "badge icon",
                modifier = Modifier.size(100.dp),
            )
            Text(
                text = badge.titel,
                fontFamily = quicksandFontFamily,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}
fun LazyListScope.errorText(
    @StringRes text: Int,
    modifier: Modifier = Modifier,
) {
    item {
        Text(
            text = stringResource(id = text),
            modifier = modifier,
        )
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize(),
    ) {
        CircularProgressIndicator(color = Color.LightGray)
    }
}

@Preview
@Composable
fun a() {
    BadgeCard(badge = Badge(1, "abcd", "icon")) {
//
    }
}
