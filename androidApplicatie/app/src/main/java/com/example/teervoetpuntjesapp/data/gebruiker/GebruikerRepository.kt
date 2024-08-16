package com.example.teervoetpuntjesapp.data.gebruiker

import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.puntje.PuntjeGet

interface GebruikerRepository {
    /**
     * Haalt een gebruiker op op basis van email en wachtwoord.
     *
     * @param email Het e-mailadres van de gebruiker.
     * @param password Het wachtwoord van de gebruiker.
     * @return Het Gebruiker object als de gebruiker gevonden wordt, anders een exception.
     */
    suspend fun getGebruikerByEmail(email: String, password: String): Gebruiker

    /**
     * Deze functie voegt een lijst van voltooide puntje ID's toe aan een gebruiker met de opgegeven ID.
     *
     * @param id Het ID van de gebruiker.
     * @param lijst Een lijst met puntje ID's.
     */
    suspend fun addPuntjes(id: Int, lijst: List<Int>)

    /**
     * Haalt de puntjes van een gebruiker op.
     *
     * @param id Het ID van de gebruiker.
     * @return Een lijst van PuntjeGet objecten.
     */
    suspend fun getGebruikerPuntjes(id: Int): List<PuntjeGet>
}