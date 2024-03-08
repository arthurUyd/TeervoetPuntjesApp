package com.example.teervoetpuntjesapp.data.badge

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class OfflineFirstBadgeRepository(
    private val badgeDao: BadgeDao,
    private val api: ApiService,
) : BadgeRepository {
    override fun getBadges(): Flow<List<Badge>> {
        return badgeDao.getAllBadges().map { badges ->
            badges.map(BadgeEntity::asDomainBadge)
        }.onEach {
            if (it.isEmpty()) {
                refreshBadges()
            }
        }
    }

    override suspend fun refreshBadges() {
        api.getBadges()
            .also { badges ->
                badgeDao.insert(badges.map(Badge::asBadgeEntity))
            }
    }
}
