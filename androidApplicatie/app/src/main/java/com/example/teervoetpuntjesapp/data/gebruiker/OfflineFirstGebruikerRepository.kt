package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Gebruiker
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
        TODO("Not yet implemented")
    }

    override suspend fun getPuntjes(id: Int): Flow<List<Int>> {
        TODO("Not yet implemented")
    }
}
