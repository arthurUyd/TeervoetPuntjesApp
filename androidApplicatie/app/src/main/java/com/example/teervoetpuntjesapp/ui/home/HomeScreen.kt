package com.example.teervoetpuntjesapp.ui.theme.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.data.AppViewModel
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.Home
}

@Composable
fun HomeScreen(
    // navController: NavHostController = rememberNavController(),
) {
    val viewModel: AppViewModel = viewModel(factory = AppViewModel.Factory)
    val gebruikers by viewModel.gebruikers
    val badges by viewModel.badges

    DisposableEffect(Unit) {
        viewModel.getBadges()
        viewModel.getGebruikers()
        onDispose {}
    }
    LaunchedEffect(badges) {
        viewModel.getBadges()
        viewModel.getGebruikers()
    }
}
