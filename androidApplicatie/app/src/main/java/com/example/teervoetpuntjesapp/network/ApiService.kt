package com.example.teervoetpuntjesapp.network

import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.Model.Puntje
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
    ): List<Int>

    @GET("puntjes")
    suspend fun getPuntjes(): List<Puntje>

    @GET("gebruikers/{email}")
    suspend fun getGebruikerByEmail(
        @Path("email") email: String,
        @Body password: String,
    ): Gebruiker

    @POST("gebruikers")
    suspend fun insertGebruiker(@Body gebruiker: Gebruiker)

    @POST("gebruikers/puntjes/{id}")
    suspend fun addGebruikerPuntjes(
        @Path("id") id: Int,
        @Body puntjesIds: List<Int>,
    )


}
