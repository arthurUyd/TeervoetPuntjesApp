package com.example.teervoetpuntjesapp.data

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.network.ApiService

interface BadgeRepository {
    suspend fun getBadges(): List<Badge>
}

class NetworkBadgeRepository(
    private val badgeApiService: ApiService,
) : BadgeRepository {
    override suspend fun getBadges(): List<Badge> = badgeApiService.getBadges()
}
