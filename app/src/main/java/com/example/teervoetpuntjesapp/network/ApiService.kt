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

    @GET("puntjes")
    suspend fun getPuntjes(): List<Puntje>
    
    @GET("gebruikers")
    suspend fun getGebruikers(): List<Gebruiker>
    
    @GET("gebruikers/{email}")
    suspend fun getGebruikerByEmail(
        @Path("email") email: String,
    ): Gebruiker

    @POST("gebruikers")
    suspend fun insertGebruiker(@Body gebruiker: Gebruiker)

    @POST("gebruikers/{id}/puntjes")
    suspend fun addPuntjes(@Path("id") id: Int, @Body puntjesIds: List<Int>)
}
