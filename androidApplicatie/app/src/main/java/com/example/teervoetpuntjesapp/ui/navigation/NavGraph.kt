package com.example.teervoetpuntjesapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.teervoetpuntjesapp.ui.badge.BadgeDetailsDestination
import com.example.teervoetpuntjesapp.ui.badge.BadgeScreen
import com.example.teervoetpuntjesapp.ui.login.LoginDestination
import com.example.teervoetpuntjesapp.ui.login.LoginForm
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeDestination
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeScreen

@Composable
fun BadgesNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier,
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(badgePress = { id -> navController.navigate(BadgeDetailsDestination.route + "/$id") })
        }
        composable(route = LoginDestination.route) {
            LoginForm(loginSuccess = { navController.navigate(HomeDestination.route) })
        }
        composable(route = BadgeDetailsDestination.route + "/{badge_id}") {
                backStackEntry ->
            val id = backStackEntry.arguments?.getString("badge_id")?.toInt() ?: return@composable
            println("getting badge with id: $id")
            BadgeScreen(id, onBackButtonClicked = { navController.navigate(HomeDestination.route) })
        }
    }
}
