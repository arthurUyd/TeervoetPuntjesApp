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

interface ApiService {

    @GET("badges")
    suspend fun getBadges(): List<Badge>

    @GET("gebruikers/puntjes/{id}")
    suspend fun getGebruikerPuntjes(
        @Path("id") id: Int,
    ): List<PuntjeGet>

    @GET("puntjes")
    suspend fun getPuntjes(): List<Puntje>

    @POST("gebruikers")
    suspend fun getGebruikerByEmail(
        @Body credentials: Credentials,
    ): Gebruiker

    @POST("gebruikers/puntjes/{id}")
    suspend fun addGebruikerPuntjes(
        @Path("id") id: Int,
        @Body puntjesIds: List<Int>,
    )
}
