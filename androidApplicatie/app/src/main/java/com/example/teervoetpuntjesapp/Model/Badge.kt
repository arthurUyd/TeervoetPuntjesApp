package com.example.teervoetpuntjesapp.Model

import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import kotlinx.serialization.Serializable

/**
 * Data class voor badges in de Teervoetpuntjes applicatie.
 */
@Serializable
data class Badge(
    /**
     * Unieke identificator van de badge.
     */
    val id: Int = 0,
    /**
     * Titel of beschrijving van de badge.
     */
    val titel: String = "",
    /**
     * URL naar de afbeelding van de badge.
     */
    val image_url: String = "",
) {
    companion object {
        /**
         * Converteert een Badge object naar een BadgeEntity object.
         *
         * @param badge De Badge die geconverteerd moet worden.
         * @return Een BadgeEntity object.
         */
        fun asBadgeEntity(badge: Badge): BadgeEntity {
            return BadgeEntity(
                badge.id,
                badge.titel,
                badge.image_url,
            )
        }
    }
}
