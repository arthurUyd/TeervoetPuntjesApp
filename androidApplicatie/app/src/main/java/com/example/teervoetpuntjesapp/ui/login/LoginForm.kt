package com.example.teervoetpuntjesapp.ui.login

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
import androidx.navigation.NavController
import com.example.teervoetpuntjesapp.Model.Credentials
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.R
import com.example.teervoetpuntjesapp.data.AppViewModel
import com.example.teervoetpuntjesapp.ui.navigation.NavigationDestination
import com.example.teervoetpuntjesapp.ui.theme.quicksandFontFamily
import java.lang.Exception

object LoginDestination : NavigationDestination {
    override val route = "login"
    override val titleRes = R.string.Login
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun LoginForm(
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
                    if (checkCredentials(credentials, gebruikers, viewModel) == true) {
                        navController.navigate("home")
                    } else {
                        Toast.makeText(context, "wrong credentials", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            )
//            LabeledCheckbox(
//                label = "Remember me",
//                onCheckedChanged = { credentials = credentials.copy(remember = !credentials.remember) },
//                isChecked = credentials.remember,
//            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    if (checkCredentials(credentials, gebruikers, viewModel) == true) {
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

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
fun checkCredentials(creds: Credentials, gebruikers: List<Gebruiker>, viewModel: AppViewModel): Boolean {
    try {
        var gebruiker = gebruikers.filter { gebruiker ->
            gebruiker.email.lowercase() == creds.login.lowercase()
        }.first()
        if (gebruiker.password == creds.pwd && creds.login.lowercase() == gebruiker.email.lowercase()) {
            viewModel.setGebruiker(gebruiker)
            return true
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return false
}

// @Composable
// fun LabeledCheckbox(
//    label: String,
//    onCheckedChanged: () -> Unit,
//    isChecked: Boolean,
// ) {
//    Row(
//        modifier = Modifier.clickable(
//            onClick = onCheckedChanged,
//        ).padding(4.dp),
//    ) {
//        Checkbox(checked = isChecked, onCheckedChange = null)
//        Spacer(modifier = Modifier.size(6.dp))
//        Text(text = label)
//    }
// }

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
