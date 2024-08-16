package com.example.teervoetpuntjesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.teervoetpuntjesapp.ui.login.LoginDestination
import com.example.teervoetpuntjesapp.ui.login.LoginFormViewModel
import com.example.teervoetpuntjesapp.ui.login.LoginResult
import com.example.teervoetpuntjesapp.ui.navigation.BadgesNavHost
import com.example.teervoetpuntjesapp.ui.theme.TeervoetPuntjesAppTheme
/**
 * Hoofdactiviteit van de Teervoetjespuntjes applicatie.
 *
 * Deze activiteit verzorgt de opbouw van de user interface met behulp van Jetpack Compose.
 * Het gebruikt de TeervoetPuntjesAppTheme om de styling van de applicatie te definiëren.
 * De activiteit maakt gebruik van een Scaffold composable om de top bar, navigatie host en padding toe te voegen.
 *
 * De rememberNavController functie wordt gebruikt om een NavController te initialiseren voor navigatie tussen schermen.
 * De SVKTopAppBar composable wordt gebruikt als top bar en toont de applicatie titel en login/logout knop afhankelijk
 * van de ingelogde status.
 * De BadgesNavHost composable verzorgt de navigatie tussen de verschillende schermen in de applicatie.
 */
class MainActivity() : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TeervoetPuntjesAppTheme {
                Scaffold(
                    topBar = { SVKTopAppBar(onLoginClicked = { navController.navigate(LoginDestination.route) }) },
                ) {
                        paddingValues ->
                    BadgesNavHost(
                        navController = navController,
                        modifier = Modifier.padding(paddingValues),
                    )
                }
            }
        }
    }
}
/**
 * Composable functie voor het weergeven van de top bar in de applicatie.
 *
 * De top bar toont de applicatie titel (FOS) en een login of logout knop afhankelijk van de
 * ingelogde status. De login knop navigeert naar het login scherm en de logout knop roept de
 * logout functie van de LoginFormViewModel aan.
 *
 * De LoginFormViewModel wordt opgehaald via viewModel() en de loginResult Flow wordt geobserveerd
 * met collectAsState() om de login status te bepalen.
 *
 * @param onLoginClicked Functie die wordt aangeroepen wanneer er op de login knop wordt geklikt.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SVKTopAppBar(onLoginClicked: () -> Unit) {
    var viewModel: LoginFormViewModel = viewModel(factory = LoginFormViewModel.Factory)
    val loginResult by viewModel.loginResult.collectAsState()

    CenterAlignedTopAppBar(
        title = {
            Text(text = stringResource(R.string.fos))
        },
        actions = {
            if (loginResult is LoginResult.Error || loginResult is LoginResult.Nothing) {
                TextButton(onClick = {
                    onLoginClicked()
                }) {
                    Text(text = stringResource(R.string.login))
                }
            } else {
                TextButton(onClick = {
                    viewModel.logout()
                }) {
                    Text(text = stringResource(R.string.logout))
                }
            }
        },
    )
}
