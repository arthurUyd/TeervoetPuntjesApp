package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.Model.Gebruiker_puntje
import kotlinx.coroutines.flow.Flow

interface GebruikerRepository {
    suspend fun getGebruikerByEmail(email: String, password: String): Flow<Gebruiker>



//    suspend fun postGebruiker(gebruiker: Gebruiker)
    suspend fun addPuntjes(id: Int, lijst: List<Int>)

    suspend fun getGebruikerPuntjes(id: Int): Flow<List<Gebruiker_puntje>>
}

