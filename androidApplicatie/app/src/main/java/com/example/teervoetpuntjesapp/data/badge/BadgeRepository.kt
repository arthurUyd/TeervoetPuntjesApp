package com.example.teervoetpuntjesapp.data.badge

import com.example.teervoetpuntjesapp.Model.Badge
import kotlinx.coroutines.flow.Flow

interface BadgeRepository {
     fun getBadges(): Flow<List<Badge>>
    suspend fun refreshBadges()
}

