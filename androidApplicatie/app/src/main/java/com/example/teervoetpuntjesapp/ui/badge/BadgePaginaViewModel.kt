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
sealed interface BadgeUiState {
    data class Success(val badge: Badge, val puntjes: List<Puntje>) : BadgeUiState
    object Error : BadgeUiState
    object Loading : BadgeUiState
}
class BadgePaginaViewModel(
    private val badgeRepository: BadgeRepository,
    private val puntjesRepository: PuntjesRepository,
) : ViewModel() {

    var badgeUiState: BadgeUiState by mutableStateOf(BadgeUiState.Loading)
        private set

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
