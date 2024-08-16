package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Credentials
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.puntje.PuntjeGet
import com.example.teervoetpuntjesapp.network.ApiService

/**
 * Deze klasse implementeert de `GebruikerRepository` interface en voert alle gebruiker gerelateerde
 * bewerkingen uit via de opgegeven `ApiService`. Alle data wordt verkregen via netwerkoproepen.
 */
class NetworkGebruikerRepository(
    private val api: ApiService,
) : GebruikerRepository {

    /**
     * Haalt een gebruiker op op basis van email en wachtwoord via de netwerk-API.
     *
     * @param email Het e-mailadres van de gebruiker.
     * @param password Het wachtwoord van de gebruiker.
     * @return Het `Gebruiker` object dat overeenkomt met de opgegeven credentials.
     */
    override suspend fun getGebruikerByEmail(email: String, password: String): Gebruiker {
        val credentials = Credentials(email, password)
        return api.getGebruikerByEmail(credentials)
    }

    /**
     * Voegt puntjes toe aan een gebruiker via de netwerk-API.
     *
     * @param id Het ID van de gebruiker.
     * @param lijst Een lijst met puntje ID's die toegevoegd moeten worden.
     */
    override suspend fun addPuntjes(id: Int, lijst: List<Int>) {
        api.addGebruikerPuntjes(id, lijst)
    }

    /**
     * Haalt de puntjes van een gebruiker op via de netwerk-API.
     *
     * @param id Het ID van de gebruiker.
     * @return Een lijst van `PuntjeGet` objecten die de puntjes van de gebruiker voorstellen.
     */
    override suspend fun getGebruikerPuntjes(id: Int): List<PuntjeGet> {
        return api.getGebruikerPuntjes(id)
    }
}
