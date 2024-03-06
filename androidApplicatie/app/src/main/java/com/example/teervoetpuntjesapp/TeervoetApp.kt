package com.example.teervoetpuntjesapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.teervoetpuntjesapp.ui.navigation.BadgesNavHost

@Composable
fun TeervoetApp(
    navController: NavHostController = rememberNavController(),
) {
    BadgesNavHost(navController = navController)
}
