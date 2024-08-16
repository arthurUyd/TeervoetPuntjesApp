package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

/**
 * Data class voor gebruikersinloggegevens.
 *
 * Bevat de inloggegevens (email en wachtwoord) voor gebruikersauthenticatie.
 */
@Serializable
data class Credentials(
    /**
     * Het e-mailadres van de gebruiker.
     */
    var email: String = "",
    /**
     * Het wachtwoord van de gebruiker.
     */
    var password: String = "",
) {
    /**
     * Deze functie controleert of zowel het e-mailadres als het wachtwoord niet leeg zijn.
     *
     * @return `true` als zowel email als wachtwoord niet leeg zijn, anders `false`.
     */
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && email.isNotEmpty()
    }
}