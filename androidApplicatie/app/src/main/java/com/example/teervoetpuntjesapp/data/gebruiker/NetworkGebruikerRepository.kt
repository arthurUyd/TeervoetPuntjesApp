package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Credentials
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.puntje.PuntjeGet
import com.example.teervoetpuntjesapp.network.ApiService

class NetworkGebruikerRepository(
    private val api: ApiService,
) : GebruikerRepository {

    override suspend fun getGebruikerByEmail(email: String, password: String): Gebruiker {
        var credentials = Credentials(email, password)
        return api.getGebruikerByEmail(credentials)
    }

    override suspend fun addPuntjes(id: Int, lijst: List<Int>) {
        api.addGebruikerPuntjes(id, lijst)
    }

    override suspend fun getGebruikerPuntjes(id: Int): List<PuntjeGet> {
        return api.getGebruikerPuntjes(id)
    }
}
