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
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val badgeRepository: BadgeRepository,
) : ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { context, exception ->
        viewModelScope.launch {
            isError.emit(true)
        }
    }

    private val badges: Flow<Result<List<Badge>>> =
        badgeRepository.getBadges().asResult()

    private val isError = MutableStateFlow(false)
    private val isRefreshing = MutableStateFlow(false)
    private val currentBadge = MutableStateFlow(0)

    val uiState: StateFlow<HomeScreenState> = combine(
        badges,
        isRefreshing,
        isError,
        currentBadge,
    ) { badgeResult, errorOccurred, refreshing, badgeInt ->
        val badgestate: BadgeUiState = when (badgeResult) {
            is Result.Success -> BadgeUiState.Success(badgeResult.data)
            is Result.Loading -> BadgeUiState.Loading
            is Result.Error -> BadgeUiState.Error
        }
        HomeScreenState(
            badgestate,
            refreshing,
            errorOccurred,
            badgeInt,
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = HomeScreenState(
            BadgeUiState.Loading,
            isRefreshing = false,
            isError = false,
            currentBadge = 0,
        ),
    )


    fun onRefresh() {
        viewModelScope.launch(exceptionHandler) {
            with(badgeRepository) {
                val refreshBadges = async { refreshBadges() }
                isRefreshing.emit(true)
                try {
                    refreshBadges
                } finally {
                    isRefreshing.emit(false)
                }
            }
        }
    }

    fun onErrorConsumed() {
        viewModelScope.launch {
            isError.emit(false)
        }
    }

    companion object {
        private var Instance: HomeScreenViewModel? = null
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
