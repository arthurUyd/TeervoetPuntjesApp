package com.example.teervoetpuntjesapp.data.puntje

import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Deze klasse haalt puntjes op uit een lokale database (Room) en synchroniseert deze met een externe API.
 * De eerste keer dat puntjes worden opgevraagd en de lokale database leeg is, worden de puntjes
 * van de API gehaald en in de database opgeslagen.
 */
class OfflineFirstPuntjesRepository(
    private val puntjeDao: PuntjeDao,
    private val api: ApiService,
) : PuntjesRepository {

    /**
     * Deze functie probeert eerst de puntjes uit de lokale database te halen.
     * Als de database leeg is, worden de puntjes van de API opgehaald en in de database opgeslagen.
     *
     * @return Een Flow van een lijst met Puntje objecten.
     */
    override suspend fun getPuntjes(): Flow<List<Puntje>> {
        return puntjeDao.getAllPuntjes()
            .map { puntjes ->
                if (puntjes.isEmpty()) {
                    refreshPuntjes()
                    emptyList()
                } else {
                    puntjes.map(PuntjeEntity::asDomainPuntje)
                }
            }
    }

    /**
     * Vernieuwt de lijst met puntjes door deze op te halen van de API en op te slaan in de Room database.
     */
    suspend fun refreshPuntjes() {
        api.getPuntjes()
            .also { puntjes ->
                puntjeDao.insert(puntjes.map(Puntje::asPuntjeEntity))
            }
    }
}