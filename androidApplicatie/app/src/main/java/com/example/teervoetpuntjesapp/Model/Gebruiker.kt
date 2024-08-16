package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

/**
 * Data class voor een gebruiker in de Teervoetpuntjes applicatie.
 *
 * Bevat informatie over een gebruiker, inclusief ID, naam, e-mail, wachtwoord en een indicator of de gebruiker een leider is.
 */
@Serializable
data class Gebruiker(
    /**
     * Het unieke identificatienummer van de gebruiker.
     */
    val id: Int,
    /**
     * De naam van de gebruiker.
     */
    val naam: String,
    /**
     * Het e-mailadres van de gebruiker.
     */
    val email: String,
    /**
     * Het wachtwoord van de gebruiker.
     */
    val password: String,
    /**
     * Een indicator of de gebruiker een leider is (1 voor leider, 0 voor niet-leider).
     */
    val isLeider: Int,
)
