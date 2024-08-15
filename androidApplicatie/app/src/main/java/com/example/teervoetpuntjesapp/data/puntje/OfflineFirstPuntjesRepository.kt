package com.example.teervoetpuntjesapp.data.puntje

import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OfflineFirstPuntjesRepository(
    private val puntjeDao: PuntjeDao,
    private val api: ApiService,
) : PuntjesRepository {
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

    suspend fun refreshPuntjes() {
        api.getPuntjes()
            .also { puntjes ->
                puntjeDao.insert(puntjes.map(Puntje::asPuntjeEntity))
            }
    }

}
