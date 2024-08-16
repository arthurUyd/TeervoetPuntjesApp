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

/**
 * ViewModel klasse voor het beheren van de login functionaliteit en gebruikersgegevens.
 *
 * Deze ViewModel verzorgt de interactie met de GebruikerRepository om gebruikers te laten inloggen,
 * uitloggen en punten toe te kennen aan andere gebruikers. De ViewModel houdt ook de login status bij
 * via de loginResult Flow en de ingelogde gebruiker via de gebruikerState Flow.
 *
 * @property userRepo [GebruikerRepository] instantie voor interactie met de gebruikers data layer.
 */
class LoginFormViewModel(
    private val userRepo: GebruikerRepository,
) : ViewModel() {
    /**
     * StateFlow voor de ingelogde gebruiker.
     *
     * gebruikerState geeft de ingelogde gebruiker weer als een Gebruiker object of null
     * als er niemand is ingelogd.
     */
    private val _gebruikerState = MutableStateFlow<Gebruiker?>(null)
    val gebruikerState: StateFlow<Gebruiker?> = _gebruikerState

    /**
     * StateFlow voor de login status.
     *
     * loginResult geeft de uitkomst van de laatste login poging weer. Mogelijke waarden zijn:
     *   * Success(gebruiker): Login was succesvol, gebruiker bevat het ingelogde Gebruiker object.
     *   * Error: Login is mislukt, bevat eventueel een foutmelding.
     *   * Nothing: Er is nog geen login poging gedaan.
     */
    private val _loginResult = MutableStateFlow<LoginResult>(LoginResult.Nothing(""))

    val loginResult: StateFlow<LoginResult> = _loginResult
    var badges: List<Int> by mutableStateOf(emptyList())

    /**
     * De lijst van badges die voor de actieve gebruiker toegevoegd zullen worden.
     */
    private var _badgesOmTeOndertekenen = MutableStateFlow<List<Int>>(emptyList())

    /**
     * Deze functie haalt alle behaalde badges voor de actieve gebruiker op.
     * De behaalde badges worden opgeslagen in de badges parameter.
     */
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

    /**
     * Inloggen met email en wachtwoord.
     *
     * Deze functie probeert in te loggen met de opgegeven email en wachtwoord. Bij succes
     * wordt de gebruiker opgeslagen in gebruikerState en de loginResult wordt gemarkeerd
     * als LoginResult.Success. Bij fouten wordt een LoginResult.Error gepusht.
     *
     * @param email Email adres van de gebruiker.
     * @param password Wachtwoord van de gebruiker.
     */
    //
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

    /**
     * De gebruiker logt uit.
     *
     * Alle gegevens worden terug naar de begintoestand gezet om een nieuwe aanmelding te kunnen voltooien.
     */
    fun logout() {
        _gebruikerState.value = null
        _loginResult.value = LoginResult.Nothing("")
        badges = emptyList()
        _badgesOmTeOndertekenen.value = emptyList()
    }

    /**
     * Punten toevoegen aan de _badgesOmTeOndertekenen.
     *
     * Indien een gebruiker op een puntje heeft geklikt zal dit puntje toegevoegd worden aan de _badgesOmTeOndertekenen.
     * Indien het puntje al in de lijst zat zal het eruit gehaald worden.
     */
    fun PuntjeClicked(id: Int) {
        val mutableList = _badgesOmTeOndertekenen.value.toMutableList()
        if (mutableList.contains(id)) {
            mutableList.remove(id)
        } else {
            mutableList.add(id)
        }
        _badgesOmTeOndertekenen.value = mutableList
    }

    /**
     * Punten toekennen aan de actieve gebruiker.
     *
     * Deze functie probeert punten toe te kennen aan de actieve gebruiker. De email en
     * wachtwoord van de inloggende leider worden gebruikt om te verifiëren of de gebruiker
     * bevoegd is om punten toe te kennen. Alleen leiders kunnen punten toekennen.
     *
     * @param email Email adres van de gebruiker die de punten toekent (leider).
     * @param password Wachtwoord van de gebruiker die de punten toekent (leider).
     */
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

    /**
     * ViewModelProvider.Factory voor het instantiëren van de LoginFormViewModel.
     *
     * Deze factory zorgt ervoor dat er slechts één instantie van de ViewModel bestaat
     * binnen dezelfde scope (bijvoorbeeld activity of fragment).
     */
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
