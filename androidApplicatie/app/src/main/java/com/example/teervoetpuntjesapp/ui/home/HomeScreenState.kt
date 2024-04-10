package com.example.teervoetpuntjesapp.ui.home

import androidx.compose.runtime.Immutable
import com.example.teervoetpuntjesapp.Model.Badge

@Immutable
sealed interface BadgeUiState {
    data class Success(val badges: List<Badge>) : BadgeUiState
    object Error : BadgeUiState
    object Loading : BadgeUiState
}

data class HomeScreenState(
    val badges: BadgeUiState,
    val isRefreshing: Boolean,
    val isError: Boolean,
    val currentBadge: Int = 0
)
