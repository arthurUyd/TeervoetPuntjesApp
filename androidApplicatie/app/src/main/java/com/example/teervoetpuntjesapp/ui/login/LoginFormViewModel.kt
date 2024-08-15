package com.example.teervoetpuntjesapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.gebruiker.GebruikerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
sealed class LoginResult {
    data class Success(val user: Gebruiker) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
class LoginFormViewModel(
    private val userRepo: GebruikerRepository,
//    private val applicationContext: Context
) : ViewModel() {

    private val _gebruikerState = MutableStateFlow<Gebruiker?>(null)
    val gebruikerState: StateFlow<Gebruiker?> = _gebruikerState
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Error(""))

    val loginResult: StateFlow<LoginResult> = _loginResult
    var badges: List<Int> by mutableStateOf(emptyList())

    fun getBadges() {
        viewModelScope.launch {
            try {
                badges = gebruikerState.value?.let { userRepo.getGebruikerPuntjes(it.id).map { it.punt_id } } ?: emptyList()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val gebruiker = userRepo.getGebruikerByEmail(email, password)
                _gebruikerState.value = gebruiker
                _loginResult.value = LoginResult.Success(gebruiker)
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }
//    fun saveUserName(userName: String) {
//        viewModelScope.launch {
//            userContext.saveUser(userName)
//        }
//    }
//    fun setGebruiker(email: String, password: String) {
//        viewModelScope.launch {
//            try {
//                val g = userRepo.getGebruikerByEmail(email, password)
//                gebruiker.value = g.first()
//                saveUserName(g.first().naam)
//            } catch (err: IOException) {
//                err.printStackTrace()
//            }
//        }
//    }

    companion object {
        private var Instance: LoginFormViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]as TeervoetApplicatie)
                    val gebruikerRepository = application.container.gebruikerRepository
                    Instance = LoginFormViewModel(gebruikerRepository)
                }
                Instance!!
            }
        }
    }
}
