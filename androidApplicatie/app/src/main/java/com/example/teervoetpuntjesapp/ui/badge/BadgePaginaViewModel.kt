package com.example.teervoetpuntjesapp.ui.badge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import com.example.teervoetpuntjesapp.data.puntje.PuntjesRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
sealed class BadgeUiState {
    data class Success(val badge: Badge, val puntjes: List<Puntje>) : BadgeUiState()
    object Error : BadgeUiState()
    object Loading : BadgeUiState()
}

/**
 * ViewModel klasse voor het beheren van de UI state van het badge detailscherm.
 *
 * @property badgeRepository [BadgeRepository] instantie voor interactie met de badge data layer.
 * @property puntjesRepository [PuntjesRepository] instantie voor interactie met de puntjes data layer.
 */
class BadgePaginaViewModel(
    private val badgeRepository: BadgeRepository,
    private val puntjesRepository: PuntjesRepository,
) : ViewModel() {

    /**
     * De huidige UI state van het scherm. Kan Loading, Success of Error zijn.
     *
     * [BadgeUiState] sealed class definieert de verschillende statussen van de UI.
     */
    var badgeUiState: BadgeUiState by mutableStateOf(BadgeUiState.Loading)
        private set

    /**
     * Haalt informatie op over een badge met het opgegeven identificatienummer.
     *
     * De functie start een coroutine om de badge informatie op te halen via de BadgeRepository.
     * Vervolgens worden de bijbehorende puntjes opgehaald via de PuntjesRepository.
     * De UI state wordt bijgewerkt op basis van het resultaat (succes, laden of fout).
     *
     * @param id Het identificatienummer van de badge.
     */
    fun getBadge(id: Int) {
        viewModelScope.launch {
            badgeUiState = try {
                val badge = badgeRepository.getBadge(id)
                val puntjes = puntjesRepository.getPuntjes().first().filter { puntje -> puntje.badge_id == id }
                BadgeUiState.Success(badge.first(), puntjes)
            } catch (e: IOException) {
                e.printStackTrace()
                BadgeUiState.Error
            } catch (e: HttpException) {
                e.printStackTrace()
                BadgeUiState.Error
            }
        }
    }

    companion object {
        private var Instance: BadgePaginaViewModel? = null

        /**
         * ViewModelProvider.Factory voor het instantiëren van de BadgePaginaViewModel.
         *
         * Deze factory zorgt ervoor dat er slechts één instantie van de ViewModel bestaat
         * binnen dezelfde scope (bijvoorbeeld activity of fragment).
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TeervoetApplicatie)
                    val puntjesrepo = application.container.puntjesRepository
                    val badgeRepository = application.container.badgeRepository

                    Instance = BadgePaginaViewModel(badgeRepository = badgeRepository, puntjesRepository = puntjesrepo)
                }
                Instance!!
            }
        }
    }
}
