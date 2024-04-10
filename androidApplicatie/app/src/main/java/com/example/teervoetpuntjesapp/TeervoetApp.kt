package com.example.teervoetpuntjesapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.teervoetpuntjesapp.ui.badge.BadgeDetailsDestination
import com.example.teervoetpuntjesapp.ui.login.LoginDestination
import com.example.teervoetpuntjesapp.ui.navigation.BadgesNavHost
import com.example.teervoetpuntjesapp.ui.theme.pages.HomeDestination

@Composable
fun TeervoetApp(
    navController: NavHostController = rememberNavController(),

) {
    val backStackEntry by navController.currentBackStackEntryAsState()

    val goHome: () -> Unit = {
        navController.popBackStack(
            HomeDestination.route,
            inclusive = false,
        )
    }

    val goToBadge = { navController.navigate(BadgeDetailsDestination.route) { launchSingleTop = true } }
    val goToLogin = { navController.navigate(LoginDestination.route) { launchSingleTop = true } }

    // kijken voor verschillen navigationTypes (perm nav, ...)

    BadgesNavHost(navController = navController)
}
