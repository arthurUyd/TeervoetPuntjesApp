package com.example.teervoetpuntjesapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.gebruiker.GebruikerRepository
import com.example.teervoetpuntjesapp.data.gebruiker.UserContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

data class UserUiState(
    val username: String,
)
class LoginFormViewModel(
    private val userRepo: GebruikerRepository,
    private val userContext: UserContext,
) : ViewModel() {

    private val gebruiker = MutableStateFlow<Gebruiker?>(null)

    fun saveUserName(userName: String) {
        viewModelScope.launch {
            userContext.saveUser(userName)
        }
    }
    fun setGebruiker(email: String, password: String) {
        viewModelScope.launch {
            try {
                val g = userRepo.getGebruikerByEmail(email, password)
                gebruiker.value = g.first()
                saveUserName(g.first().naam)
            } catch (err: IOException) {
                err.printStackTrace()
            }
        }
    }

    companion object {
        private var Instance: LoginFormViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as TeervoetApplicatie)
                    val gebruikerRepository = application.container.gebruikerRepository
                    Instance = LoginFormViewModel(gebruikerRepository, application.userContext)
                }
                Instance!!
            }
        }
    }
}
