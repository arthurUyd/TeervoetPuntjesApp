package com.example.teervoetpuntjesapp.data.badge

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class OfflineFirstBadgeRepository(
    private val badgeDao: BadgeDao,
    private val api: ApiService,
) : BadgeRepository {
    override fun getBadges(): Flow<List<Badge>> {
        return badgeDao.getAllBadges()
            .map { badges ->
                if (badges.isEmpty()) {
                    println("getting badges from network")
                    refreshBadges() // Call network here if empty
                    emptyList() // Emit empty list immediately
                } else {
                    println("getting badges from room")
                    badges.map(BadgeEntity::asDomainBadge)
                }
            }
    }

    override fun getBadge(id: Int): Flow<Badge> {
        return badgeDao.getBadge(id)
            .map(BadgeEntity::asDomainBadge)
            .catch { e ->
                e.printStackTrace()
            }
    }
    override suspend fun refreshBadges() {
        api.getBadges()
            .also { badges ->
                badgeDao.insert(badges.map(Badge::asBadgeEntity))
            }
    }
}
