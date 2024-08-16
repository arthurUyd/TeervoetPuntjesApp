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
import com.example.teervoetpuntjesapp.ui.login.OndertekenLoginDestination
import com.example.teervoetpuntjesapp.ui.login.OndertekenLoginForm
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeDestination
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeScreen
/**
 * NavHost composable voor het navigeren tussen verschillende schermen in de applicatie.
 *
 * Deze composable definieert de verschillende navigatiebestemmingen en hun bijbehorende schermen.
 * Het gebruikt de NavHostController om de navigatie tussen schermen te beheren.
 *
 * @param navController De NavHostController voor het beheren van de navigatie.
 * @param modifier Optionele modifier om de stijl van de composable aan te passen.
 */
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
        composable(route = OndertekenLoginDestination.route) {
            OndertekenLoginForm(loginSuccess = { navController.navigate(HomeDestination.route) })
        }
        composable(route = BadgeDetailsDestination.route + "/{badge_id}") {
                backStackEntry ->
            val id = backStackEntry.arguments?.getString("badge_id")?.toInt() ?: return@composable
            BadgeScreen(id, onBackButtonClicked = { navController.navigate(HomeDestination.route) }, onOndertekenClicked = { navController.navigate(OndertekenLoginDestination.route) })
        }
    }
}
