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
    data class Nothing(val message: String) : LoginResult()
}
class LoginFormViewModel(
    private val userRepo: GebruikerRepository,
) : ViewModel() {

    private val _gebruikerState = MutableStateFlow<Gebruiker?>(null)
    val gebruikerState: StateFlow<Gebruiker?> = _gebruikerState
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Nothing(""))

    val loginResult: StateFlow<LoginResult> = _loginResult
    var badges: List<Int> by mutableStateOf(emptyList())

    private var _badgesOmTeOndertekenen = MutableStateFlow<List<Int>>(emptyList())

    fun getBadges() {
        viewModelScope.launch {
            try {
                var badgeslist = gebruikerState.value?.let { userRepo.getGebruikerPuntjes(it.id) } ?: emptyList()
                badges = badges + badgeslist.map { it.punt_id }
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
                println(gebruiker)
                _gebruikerState.value = gebruiker
                _loginResult.value = LoginResult.Success(gebruiker)
                println(_loginResult.value)
            } catch (e: IOException) {
                e.printStackTrace()
                _loginResult.value = LoginResult.Error("")
            } catch (e: HttpException) {
                e.printStackTrace()
                _loginResult.value = LoginResult.Error("")
            }
        }
    }

    fun logout() {
        _gebruikerState.value = null
        _loginResult.value = LoginResult.Error("")
        badges = emptyList()
        _badgesOmTeOndertekenen.value = emptyList()
    }

    fun PuntjeClicked(id: Int) {
        val mutableList = _badgesOmTeOndertekenen.value.toMutableList()
        if (mutableList.contains(id)) {
            mutableList.remove(id)
        } else {
            mutableList.add(id)
        }
        _badgesOmTeOndertekenen.value = mutableList
    }

    fun AddPuntjes(email: String, password: String) {
        viewModelScope.launch {
            try {
                val gebruiker = userRepo.getGebruikerByEmail(email, password)
                if (gebruiker.isLeider == 1) {
                    gebruikerState.value?.let { userRepo.addPuntjes(it.id, _badgesOmTeOndertekenen.value) }
                    badges = badges + _badgesOmTeOndertekenen.value
                    _badgesOmTeOndertekenen.value = emptyList()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
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
                    Instance = LoginFormViewModel(gebruikerRepository)
                }
                Instance!!
            }
        }
    }
}
