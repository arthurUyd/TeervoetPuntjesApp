package com.example.teervoetpuntjesapp.data.puntje

import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class OfflineFirstPuntjesRepository(
    private val puntjeDao: PuntjeDao,
    private val api: ApiService
) : PuntjesRepository{
    override suspend fun getPuntjes(): Flow<List<Puntje>>{
        return puntjeDao.getAllPuntjes().map {puntjes ->
            puntjes.map(PuntjeEntity::asDomainPuntje)
        }.onEach {
         if(it.isEmpty()){
             refreshPuntjes()
         }
        }
    }

    suspend fun refreshPuntjes(){
        api.getPuntjes()
            .also { puntjes ->
                puntjeDao.insert(puntjes.map(Puntje::asPuntjeEntity))
            }
    }
    fun getPuntjesVoorBadge(id: Int): Flow<List<Puntje>> = puntjeDao.getPuntjesVoorBadge(id).map{
        puntjes -> puntjes.map(PuntjeEntity::asDomainPuntje)
    }


}