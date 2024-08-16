package com.example.teervoetpuntjesapp.ui.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.teervoetpuntjesapp.Model.Credentials
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily
import kotlin.Exception
object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.Login
}

/**
 * Composable functie voor het weergeven van het loginformulier.
 *
 * Deze composable stelt gebruikers in staat om hun inloggegevens (e-mail en wachtwoord) in te voeren.
 * Het behandelt ook het klikken op de login-knop en roept de LoginFormViewModel aan om de loginactie uit te voeren.
 *
 * @param loginSuccess Functie die wordt aangeroepen wanneer de login succesvol is.
 * @param viewModel De LoginFormViewModel instantie voor de loginlogica.
 */
@Composable
fun LoginForm(
    loginSuccess: () -> Unit,
    viewModel: LoginFormViewModel = viewModel(factory = LoginFormViewModel.Factory),
) {
    val loginResult by viewModel.loginResult.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = loginResult) {
        if (loginResult is LoginResult.Success) {
            loginSuccess()
            Toast.makeText(context, "Login SuccesFull!", Toast.LENGTH_SHORT).show()
        } else if (loginResult is LoginResult.Error) {
            Toast.makeText(context, "wrong credentials", Toast.LENGTH_SHORT).show()
        }
    }
    Surface {
        var credentials by remember { mutableStateOf(Credentials()) }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
        ) {
            LoginField(
                value = credentials.email,
                onChange = { data -> credentials = credentials.copy(email = data) },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                value = credentials.password,
                onChange = { data -> credentials = credentials.copy(password = data) },
                submit = {
                    try {
                        viewModel.login(credentials.email, credentials.password)
                    } catch (e: Exception) {
                        println("error for login")
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    try {
                        viewModel.login(credentials.email, credentials.password)
                    } catch (e: Exception) {
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

/**
 * Deze composable stelt gebruikers in staat om hun e-mailadres in te voeren.
 *
 * @param value De huidige waarde van het invoerveld.
 * @param onChange Functie die wordt aangeroepen wanneer de waarde van het invoerveld verandert.
 * @param modifier Modifier om de stijl van de composable aan te passen. (optioneel)
 * @param label Labeltekst die boven het invoerveld wordt weergegeven. (standaard: "Login")
 * @param placeholder Placeholder tekst die in het invoerveld wordt weergegeven. (standaard: "Voer je login in")
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Login",
    placeholder: String = "Enter your login",
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimaryContainer) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = quicksandFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,

            )
        },
        label = {
            Text(
                text = label,
                fontFamily = quicksandFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,

            )
        },
        singleLine = true,
        visualTransformation = VisualTransformation.None,

    ) }

/**
 * Composable functie voor het weergeven van een wachtwoord invoerveld.
 *
 * Deze composable stelt gebruikers in staat om hun wachtwoord in te voeren. Het biedt de mogelijkheid
 * om de zichtbaarheid van het wachtwoord te wisselen met behulp van een pictogram.
 *
 * @param value De huidige waarde van het wachtwoordveld.
 * @param onChange Functie die wordt aangeroepen wanneer de waarde van het wachtwoord verandert.
 * @param modifier Modifier om de stijl van de composable aan te passen. (optioneel)
 * @param label Labeltekst die boven het invoerveld wordt weergegeven. (standaard: "Wachtwoord")
 * @param placeholder Placeholder tekst die in het invoerveld wordt weergegeven. (standaard: "Voer je wachtwoord in")
 * @param submit Functie die wordt aangeroepen wanneer de gebruiker op "Enter" drukt of op de submit knop klikt.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password",
    submit: () -> Unit,
) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = { Icon(imageVector = Icons.Default.Key, contentDescription = "", tint = MaterialTheme.colorScheme.onPrimaryContainer) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { submit() },
        ),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ),
        trailingIcon = {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        },
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = quicksandFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
            )
        },
        label = {
            Text(
                text = label,
                fontFamily = quicksandFontFamily,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer,

            )
        },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),

    ) }
object OndertekenLoginDestination : NavigationDestination {
    override val route = "onderteken"
    override val titleRes = R.string.onderteken
}

/**
 * Composable functie voor het weergeven van het onderteken formulier.
 *
 * Deze composable stelt gebruikers in staat om hun inloggegevens (e-mail en wachtwoord) in te voeren.
 * Het behandelt ook het klikken op de login-knop en roept de LoginFormViewModel aan om de onderteken actie uit te voeren.
 *
 * @param loginSuccess Functie die wordt aangeroepen wanneer de login succesvol is.
 * @param viewModel De LoginFormViewModel instantie voor de loginlogica.
 *
 */
@Composable
fun OndertekenLoginForm(
    loginSuccess: () -> Unit,
    viewModel: LoginFormViewModel = viewModel(factory = LoginFormViewModel.Factory)
) {
    val context = LocalContext.current

    Surface {
        var credentials by remember { mutableStateOf(Credentials()) }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize().padding(horizontal = 30.dp),
        ) {
            LoginField(
                value = credentials.email,
                onChange = { data -> credentials = credentials.copy(email = data) },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordField(
                value = credentials.password,
                onChange = { data -> credentials = credentials.copy(password = data) },
                submit = {
                    try {
                        viewModel.AddPuntjes(credentials.email, credentials.password)
                        loginSuccess()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Gebruiker is geen leider", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    try {
                        viewModel.AddPuntjes(credentials.email, credentials.password)
                        loginSuccess()
                    } catch (e: Exception) {
                        Toast.makeText(context, "Gebruiker is geen leider", Toast.LENGTH_SHORT).show()
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
