package com.example.teervoetpuntjesapp.data.badge

import com.example.teervoetpuntjesapp.Model.Badge
import kotlinx.coroutines.flow.Flow

interface BadgeRepository {
    suspend fun getBadges(): Flow<List<Badge>>
}

