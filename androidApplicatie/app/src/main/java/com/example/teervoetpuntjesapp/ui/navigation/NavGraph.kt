package com.example.teervoetpuntjesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teervoetpuntjesapp.ui.badge.BadgeDetailsDestination
import com.example.teervoetpuntjesapp.ui.badge.BadgePagina
import com.example.teervoetpuntjesapp.ui.login.LoginDestination
import com.example.teervoetpuntjesapp.ui.login.LoginForm
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeDestination
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeScreen

@Composable
fun BadgesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(navController = navController, startDestination = HomeDestination.route, modifier = modifier) {
        composable(route = HomeDestination.route) {
            HomeScreen()
        }
//        composable(route = LoginDestination.route) {
//            LoginForm(gebruikers = a, navController = a, viewModel = a)
//        }
//        composable(route = BadgeDetailsDestination.route) {
//            BadgePagina(badge = , viewModel = , navController = )
//        }
    }
}
