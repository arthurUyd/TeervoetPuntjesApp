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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.teervoetpuntjesapp.ui.login.LoginDestination
import com.example.teervoetpuntjesapp.ui.login.LoginFormViewModel
import com.example.teervoetpuntjesapp.ui.login.LoginResult
import com.example.teervoetpuntjesapp.ui.navigation.BadgesNavHost
import com.example.teervoetpuntjesapp.ui.theme.TeervoetPuntjesAppTheme

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
            TextButton(onClick = {
                onLoginClicked()
            }) {
                Text(text = if (loginResult is LoginResult.Success) stringResource(R.string.logout) else stringResource(R.string.login))
            }
        },
    )
}
