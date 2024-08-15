package com.example.teervoetpuntjesapp.data

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestBadgeRepository : BadgeRepository {
    // backing hot flow for list of badges for testing
    private val badgeFlow: MutableSharedFlow<List<Badge>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getBadges(): Flow<List<Badge>> = badgeFlow
    override fun getBadge(id: Int): Flow<Badge> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshBadges() {
        //no implementation
    }

    fun sendBadges(badges: List<Badge>){
        badgeFlow.tryEmit(badges)
    }
}
