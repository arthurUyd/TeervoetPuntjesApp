package com.example.teervoetpuntjesapp.data

import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.network.ApiService

interface GebruikerRepository {
    suspend fun getGebruikers(): List<Gebruiker>
    suspend fun getGebruikerByEmail(email: String): Gebruiker

    suspend fun postGebruiker(gebruiker: Gebruiker)
}

class NetworkGebruikerRepository(
    val gebruikerApiService: ApiService,
) : GebruikerRepository {
    override suspend fun getGebruikers(): List<Gebruiker> = gebruikerApiService.getGebruikers()

    override suspend fun getGebruikerByEmail(email: String): Gebruiker = gebruikerApiService.getGebruikerByEmail(email)

    override suspend fun postGebruiker(gebruiker: Gebruiker) = gebruikerApiService.insertGebruiker(gebruiker)
}
