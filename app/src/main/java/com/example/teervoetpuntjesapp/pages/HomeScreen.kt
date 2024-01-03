package com.example.teervoetpuntjesapp.ui.theme.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.componenten.BadgeLijst
import com.example.teervoetpuntjesapp.data.AppViewModel
import com.example.teervoetpuntjesapp.pages.BadgePagina
import com.example.teervoetpuntjesapp.pages.LoginForm
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
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

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                ),
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.lo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .size(100.dp),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "TeervoetBadges",
                            fontFamily = quicksandFontFamily,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                        )
                        Spacer(modifier = Modifier.weight(2f))
                    }
                },
            )
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
        ) {
            composable("home") {
                Box(
                    modifier = Modifier.padding(innerPadding),
                ) {
                    BadgeLijst(badges = badges, navController = navController, viewModel = viewModel)
                }
            }
            composable("login") {
                Box(
                    modifier = Modifier.padding(innerPadding),
                ) {
                    LoginForm(gebruikers, navController, viewModel)
                }
            }
            composable("puntjes/{badge}") {
                Box(
                    modifier = Modifier.padding(innerPadding),
                ) {
                    BadgePagina(badge = viewModel.geselecteerdeBadge.value, viewModel = viewModel, navController = navController)
                }
            }
        }
    }
}
