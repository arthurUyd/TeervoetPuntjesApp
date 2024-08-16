package com.example.teervoetpuntjesapp.ui.home

import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.core.Result
import com.example.teervoetpuntjesapp.core.asResult
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
data class HomeScreenState(
    val badges: BadgeUiState,
    val isRefreshing: Boolean,
    val isError: Boolean,
)

sealed interface BadgeUiState {
    data class Success(val badges: List<Badge>) : BadgeUiState
    object Error : BadgeUiState
    object Loading : BadgeUiState
}

/**
 * ViewModel klasse voor het beheren van de UI state van het home scherm.
 *
 * De ViewModel is verantwoordelijk voor het ophalen van badge informatie via de BadgeRepository.
 * De UI state hangt af van de opgehaald badges.
 *
 * @property badgeRepository [BadgeRepository] instantie voor interactie met de badge data layer.
 */
class HomeScreenViewModel(
    private val badgeRepository: BadgeRepository,
) : ViewModel() {
    /**
     * Flow van Result objecten die de uitkomst van het ophalen van badges representeren (Success, Loading of Error).
     *
     * De badges Flow wordt opgehaald via de badgeRepository.getBadges() functie en geconverteerd
     * naar een Result Flow met behulp van de asResult extensie functie.
     */
    private val badges: Flow<Result<List<Badge>>> =
        badgeRepository.getBadges().asResult()

    /**
     * StateFlow booleans voor het bijhouden van de laad en fout status.
     */
    private val isError = MutableStateFlow(false)
    private val isRefreshing = MutableStateFlow(false)

    /**
     * StateFlow voor de gecombineerde UI state van het home scherm.
     *
     * De uiState Flow combineert de badges Flow, isRefreshing, isError en currentBadge Flow.
     * Op basis van de gecombineerde waardes wordt de uiteindelijke BadgeUiState bepaald
     * (Success, Loading of Error) en gecombineerd met de laad, fout en currentBadge status tot
     * een HomeScreenState object.
     */
    val uiState: StateFlow<HomeScreenState> = combine(
        badges,
        isRefreshing,
        isError,
    ) { badgeResult, errorOccurred, refreshing ->
        val badgestate: BadgeUiState = when (badgeResult) {
            is Result.Success -> BadgeUiState.Success(badgeResult.data)
            is Result.Loading -> BadgeUiState.Loading
            is Result.Error -> BadgeUiState.Error
        }
        HomeScreenState(
            badgestate,
            refreshing,
            errorOccurred,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeScreenState(
            BadgeUiState.Loading,
            isRefreshing = false,
            isError = false,
        ),
    )

    /**
     * Functie om de isError state te resetten nadat een foutmelding is getoond.
     */
    fun onErrorConsumed() {
        viewModelScope.launch {
            isError.emit(false)
        }
    }

    companion object {
        private var Instance: HomeScreenViewModel? = null

        /**
         * ViewModelProvider.Factory voor het instantiëren van de HomePaginaViewModel.
         *
         * Deze factory zorgt ervoor dat er slechts één instantie van de ViewModel bestaat
         * binnen dezelfde scope (bijvoorbeeld activity of fragment).
         */
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TeervoetApplicatie)
                    val badgeRepository = application.container.badgeRepository
                    Instance = HomeScreenViewModel(badgeRepository = badgeRepository)
                }
                Instance!!
            }
        }
    }
}
