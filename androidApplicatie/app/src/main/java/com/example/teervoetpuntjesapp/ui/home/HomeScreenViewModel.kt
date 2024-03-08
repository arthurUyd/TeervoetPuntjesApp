package com.example.teervoetpuntjesapp.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException

class HomeScreenViewModel(private val badgeRepository: BadgeRepository) : ViewModel() {

    lateinit var uiBadgeListState: StateFlow<BadgeListState>
    var badgeApiState: BadgeApiState by mutableStateOf(BadgeApiState.Loading)
        private set

    init {
        getRepoBadges()
    }
    private fun getRepoBadges() {
        try {
            viewModelScope.launch { badgeRepository.refreshBadges() }
            uiBadgeListState = badgeRepository.getBadges().map { BadgeListState(it) }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5_000L),
                    initialValue = BadgeListState(),
                )
            badgeApiState = BadgeApiState.Success
        } catch (e: IOException) {
            badgeApiState = BadgeApiState.Error
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
