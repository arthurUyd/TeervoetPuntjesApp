package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.Model.Gebruiker_puntje
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class OfflineFirstGebruikerRepository(
    private val gebruikerDao: GebruikerDao,
    private val gpDao: GebruikerPuntjeDao,
    private val api: ApiService,
) : GebruikerRepository {

    override suspend fun getGebruikerByEmail(email: String, password: String): Flow<Gebruiker> {
        return gebruikerDao.getGebruiker(email, password).map {
            it.asDomainGebruiker()
        }.onEach {
            if (it == null) {
                refreshGebruiker(email, password)
            }
        }
    }

    suspend fun refreshGebruiker(email: String, password: String) {
        api.getGebruikerByEmail(email, password)
            .also {
                gebruikerDao.insert(it.asGebruikerEntity())
            }
    }

//    override suspend fun postGebruiker(gebruiker: Gebruiker) = gebruikerDao.insert(gebruiker)
    override suspend fun addPuntjes(id: Int, lijst: List<Int>) {
        lijst.forEach { puntje ->
            gpDao.addPuntje(Gebruiker_PuntjeEntity(id, puntje))
        }.also {
            api.addGebruikerPuntjes(id, lijst)
        }
    }

    override suspend fun getGebruikerPuntjes(id: Int): Flow<List<Gebruiker_puntje>> {
        return gpDao.getGebruikerPuntjes(id).map { puntjes ->
            puntjes.map(Gebruiker_PuntjeEntity::asDomainGP)
        }.onEach {
            if (it.isEmpty()){
                refreshGP(id)
            }
        }
    }



    suspend fun refreshGP(id: Int){
        api.getGebruikerPuntjes(id)
            .also {
                addPuntjes(id, it)
            }
    }
}
