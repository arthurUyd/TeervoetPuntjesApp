package com.example.teervoetpuntjesapp.data.badge

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * BadgeRepository implementatie die gebruik maakt van offline-first strategie.
 *
 * Deze klasse implementeert de BadgeRepository interface en haalt badges op uit een lokale
 * database (Room) gecombineerd met ophalen van een externe API.
 */
class OfflineFirstBadgeRepository(
    private val badgeDao: BadgeDao,
    private val api: ApiService,
) : BadgeRepository {

    /**
     * Deze functie haalt eerst de badges op uit de lokale database (Room).
     * Indien de lokale database leeg is, worden de badges opgehaald van de externe API
     * via de `refreshBadges` functie en een lege lijst wordt direct doorgegeven.
     * Als er wel badges in de database aanwezig zijn, worden deze geconverteerd naar Badge
     * objecten en doorgegeven.
     *
     * @return Flow<List<Badge>> Een Flow van een lijst met Badge objecten.
     */
    override fun getBadges(): Flow<List<Badge>> {
        return badgeDao.getAllBadges()
            .map { badges ->
                if (badges.isEmpty()) {
                    println("getting badges from network")
                    refreshBadges()
                    emptyList()
                } else {
                    println("getting badges from room")
                    badges.map(BadgeEntity::asDomainBadge)
                }
            }
    }

    /**
     * Deze functie haalt een specifieke badge op uit de lokale database (Room).
     * De opgehaalde BadgeEntity wordt geconverteerd naar een Badge object en doorgegeven.
     * Bij een fout wordt de fout afgedrukt (println).
     * Hier is er geen netwerk implementatie want deze zal altijd waarden uit de room database kunnen halen
     * door de implementatie van de applicatie.
     *
     * @param id De ID van de badge die opgehaald moet worden.
     * @return Flow<Badge> Een Flow van een Badge object.
     */
    override fun getBadge(id: Int): Flow<Badge> {
        return badgeDao.getBadge(id)
            .map(BadgeEntity::asDomainBadge)
            .catch { e ->
                e.printStackTrace()
            }
    }

    /**
     * Deze functie haalt de badges op van de externe API via de `api.getBadges()` methode.
     * De opgehaalde badges worden vervolgens geconverteerd naar BadgeEntity objecten
     * en opgeslagen in de lokale database (Room).
     *
     * @suspend Deze functie is suspendable omdat ze wacht op netwerkcommunicatie.
     */
    override suspend fun refreshBadges() {
        api.getBadges()
            .also { badges ->
                badgeDao.insert(badges.map(Badge::asBadgeEntity))
            }
    }
}
