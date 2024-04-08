package com.example.teervoetpuntjesapp.ui.theme.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.ui.home.BadgeUiState
import com.example.teervoetpuntjesapp.ui.home.HomeScreenState
import com.example.teervoetpuntjesapp.ui.home.HomeScreenViewModel
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.Home
}

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
    modifier: Modifier = Modifier,
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
            is BadgeUiState.Success -> BadgeList(badgeUiState = uiState.badges)
        }
    }
}

@Composable
fun BadgeList(modifier: Modifier = Modifier, badgeUiState: BadgeUiState) {
    val badgeList = badgeUiState.
}
