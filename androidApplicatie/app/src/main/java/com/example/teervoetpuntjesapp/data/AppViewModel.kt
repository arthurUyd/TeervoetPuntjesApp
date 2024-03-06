package com.example.teervoetpuntjesapp.data

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import com.example.teervoetpuntjesapp.data.gebruiker.GebruikerRepository
import com.example.teervoetpuntjesapp.data.puntje.PuntjesRepository
import kotlinx.coroutines.launch
import java.io.IOException

class AppViewModel(
    private val badgeRepository: BadgeRepository,
    private val puntjesRepository: PuntjesRepository,
    private val gebruikerRepository: GebruikerRepository,
) : ViewModel() {

    private val _badges = mutableStateOf<List<Badge>>(emptyList())
    val badges: State<List<Badge>> get() = _badges

    private val _puntjes = mutableStateOf<List<Puntje>>(emptyList())
    val puntjes: State<List<Puntje>> get() = _puntjes

    private val _gebruikers = mutableStateOf<List<Gebruiker>>(emptyList())
    val gebruikers: State<List<Gebruiker>> get() = _gebruikers

    val huidigeGebruiker = mutableStateOf<Gebruiker?>(null)

    val geselecteerdeBadge = mutableStateOf<Badge?>(null)

    val behaaldePuntjes = mutableStateListOf<Int>()

    init {
        getGebruikers()
    }
    
    fun getBadges() {
        viewModelScope.launch {
            try {
                val listResult = badgeRepository.getBadges()
                _badges.value = listResult
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun getPuntjes() {
        viewModelScope.launch {
            try {
                val listResult = puntjesRepository.getPuntjes()
                _puntjes.value = listResult
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    fun getGebruikers() {
        viewModelScope.launch {
            try {
                val listResult = gebruikerRepository.getGebruikers()
                _gebruikers.value = listResult
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun setGebruiker(gebruiker: Gebruiker) {
        viewModelScope.launch {
            try {
                val result = gebruikerRepository.getGebruikerByEmail(gebruiker.email)
                huidigeGebruiker.value = result
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
    }



    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun persistPuntjes() {
        viewModelScope.launch {
            try {
                huidigeGebruiker.value?.let { gebruikerRepository.addPuntjes(it.id, behaaldePuntjes.toList()) }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: HttpException) {
                e.printStackTrace()
            }
        }
        huidigeGebruiker.value?.let { setGebruiker(it) }
        behaaldePuntjes.clear()
    }

    fun setBadge(badge: Badge) {
        geselecteerdeBadge.value = badge
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TeervoetApplicatie)
                AppViewModel(
                    badgeRepository = application.container.badgeRepository,
                    puntjesRepository = application.container.puntjesRepository,
                    gebruikerRepository = application.container.gebruikerRepository,
                )
            }
        }
    }
}
