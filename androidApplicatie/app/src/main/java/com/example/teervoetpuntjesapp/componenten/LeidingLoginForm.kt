package com.example.teervoetpuntjesapp.componenten

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.teervoetpuntjesapp.Model.Credentials
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.AppViewModel
import com.example.teervoetpuntjesapp.pages.LoginField
import com.example.teervoetpuntjesapp.pages.PasswordField
import java.lang.Exception

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun LeidingLoginForm(
    gebruikers: List<Gebruiker>,
    navController: NavController,
    viewModel: AppViewModel,
) {
    Surface {
        var credentials by remember { mutableStateOf(Credentials()) }
        val context = LocalContext.current

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
        ) {
            LoginField(
                value = credentials.login,
                onChange = { data -> credentials = credentials.copy(login = data) },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                value = credentials.pwd,
                onChange = { data -> credentials = credentials.copy(pwd = data) },
                submit = { 
                    if (checkLeidingCredentials(credentials, gebruikers) == true) {
                        viewModel.persistPuntjes()
                        navController.navigate("home")
                    } else {
                        Toast.makeText(context, "wrong credentials", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (checkLeidingCredentials(credentials, gebruikers) == true) {
                        viewModel.persistPuntjes()
                        navController.navigate("home")
                    } else {
                        Toast.makeText(context, "wrong credentials", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = credentials.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Login")
            }
        }
    }
}

fun checkLeidingCredentials(creds: Credentials, gebruikers: List<Gebruiker>): Boolean {
    try {
        var gebruiker = gebruikers.filter { gebruiker ->
            gebruiker.email.lowercase() == creds.login.lowercase()
        }.first()
        if (gebruiker.password == creds.pwd && creds.login.lowercase() == gebruiker.email.lowercase() && gebruiker.isLeider == 1) {
            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return false
}
