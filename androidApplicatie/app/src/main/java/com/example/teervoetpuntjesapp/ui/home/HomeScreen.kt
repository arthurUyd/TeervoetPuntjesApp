package com.example.teervoetpuntjesapp.ui.theme.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.ui.home.HomeScreenViewModel
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.Home
}

@Composable
fun HomeScreen(
    // navController: NavHostController = rememberNavController(),
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
    modifier: Modifier = Modifier,
) {
    val badgeListState by homeScreenViewModel.uiBadgeListState.collectAsState()

    val badgeApiState = homeScreenViewModel.badgeApiState

    DisposableEffect(Unit) {
        onDispose {}
    }

}
