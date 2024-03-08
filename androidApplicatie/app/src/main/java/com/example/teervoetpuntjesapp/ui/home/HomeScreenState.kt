package com.example.teervoetpuntjesapp.ui.home

import com.example.teervoetpuntjesapp.Model.Badge

data class BadgeListState(
    val badgeList: List<Badge> = listOf(),
)

sealed interface BadgeApiState {
    object Success : BadgeApiState
    object Error : BadgeApiState
    object Loading : BadgeApiState
}
