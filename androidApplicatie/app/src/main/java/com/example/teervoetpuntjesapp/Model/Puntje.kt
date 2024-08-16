package com.example.teervoetpuntjesapp.Model

import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity
import kotlinx.serialization.Serializable

/**
 * Data class voor een Puntje in de Teervoetpuntjes applicatie.
 */
@Serializable
data class Puntje(
    /**
     * Het unieke identificatienummer van het puntje.
     */
    val id: Int,
    /**
     * De titel of beschrijving van het puntje.
     */
    val titel: String,
    /**
     * Het identificatienummer van de badge die aan het puntje gekoppeld is.
     */
    val badge_id: Int,
) {
    companion object {
        /**
         * Converteert een Puntje object naar een PuntjeEntity object.
         *
         * @param puntje Het Puntje object dat geconverteerd moet worden.
         * @return Een PuntjeEntity object.
         */
        fun asPuntjeEntity(puntje: Puntje): PuntjeEntity {
            return PuntjeEntity(
                puntje.id,
                puntje.titel,
                puntje.badge_id
            )
        }
    }
}
