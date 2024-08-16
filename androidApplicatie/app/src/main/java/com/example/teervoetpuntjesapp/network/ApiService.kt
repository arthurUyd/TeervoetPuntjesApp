package com.example.teervoetpuntjesapp.network

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.Model.Credentials
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.data.puntje.PuntjeGet
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Deze interface bevat functies die corresponderen met verschillende HTTP requests naar de backend
 * voor het ophalen en beheren van badges, gebruikers en puntjes.
 */
interface ApiService {

    /**
     * Haalt een lijst van alle badges op.
     *
     * @return Een lijst van Badge objecten.
     */
    @GET("badges")
    suspend fun getBadges(): List<Badge>

    /**
     * Haalt een lijst van puntjes op die aan een specifieke gebruiker gekoppeld zijn.
     *
     * @param id Het unieke identificatienummer van de gebruiker.
     * @return Een lijst van PuntjeGet objecten die aan de gebruiker gekoppeld zijn.
     */
    @GET("gebruikers/puntjes/{id}")
    suspend fun getGebruikerPuntjes(
        @Path("id") id: Int,
    ): List<PuntjeGet>

    /**
     * Haalt een lijst van alle puntjes op.
     *
     * @return Een lijst van Puntje objecten.
     */
    @GET("puntjes")
    suspend fun getPuntjes(): List<Puntje>

    /**
     * Haalt een gebruiker op aan de hand van de opgegeven inloggegevens.
     *
     * @param credentials De inloggegevens van de gebruiker.
     * @return Het Gebruiker object dat overeenkomt met de opgegeven inloggegevens.
     */
    @POST("gebruikers")
    suspend fun getGebruikerByEmail(
        @Body credentials: Credentials,
    ): Gebruiker

    /**
     * Voegt een lijst van puntjes toe aan een specifieke gebruiker.
     *
     * @param id Het unieke identificatienummer van de gebruiker.
     * @param puntjesIds Een lijst van puntje identificatienummers die toegevoegd moeten worden.
     */
    @POST("gebruikers/puntjes/{id}")
    suspend fun addGebruikerPuntjes(
        @Path("id") id: Int,
        @Body puntjesIds: List<Int>,
    )
}