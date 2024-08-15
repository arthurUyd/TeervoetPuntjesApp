package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.puntje.PuntjeGet

interface GebruikerRepository {
    suspend fun getGebruikerByEmail(email: String, password: String): Gebruiker

    suspend fun addPuntjes(id: Int, lijst: List<Int>)

    suspend fun getGebruikerPuntjes(id: Int): List<PuntjeGet>
}
