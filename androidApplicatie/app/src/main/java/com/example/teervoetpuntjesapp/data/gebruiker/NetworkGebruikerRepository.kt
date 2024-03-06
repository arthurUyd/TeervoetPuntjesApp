package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.network.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NetworkGebruikerRepository(
    val gebruikerApiService: ApiService,
) : GebruikerRepository {

    override suspend fun getGebruikerByEmail(email: String, password: String): Flow<Gebruiker?> = flow { emit(gebruikerApiService.getGebruikerByEmail(email, password)) }

    override suspend fun postGebruiker(gebruiker: Gebruiker) = gebruikerApiService.insertGebruiker(gebruiker)
    override suspend fun addPuntjes(id: Int, lijst: List<Int>) = gebruikerApiService.addGebruikerPuntjes(id, lijst)
    override suspend fun getPuntjes(id: Int): Flow<List<Int>> = flow { gebruikerApiService.getGebruikerPuntjes(id) }
}
