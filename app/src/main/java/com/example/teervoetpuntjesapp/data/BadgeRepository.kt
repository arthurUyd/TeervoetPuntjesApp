package com.example.teervoetpuntjesapp.data

import android.content.Context
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.data.database.BadgeDao
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow

interface BadgeRepository {
    suspend fun getBadges(): Flow<List<Badge>>

    suspend fun refresh()
}

class NetworkBadgeRepository(
    private val badgeApiService: ApiService,
) : BadgeRepository {
    override suspend fun getBadges(): Flow<List<Badge>> = badgeApiService.getBadges()
}


class CachingBadgeRepository(private val badgeDao: BadgeDao, private val badgeApiService: ApiService, context: Context): BadgeRepository{

    over
}
