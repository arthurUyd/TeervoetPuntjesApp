package com.example.teervoetpuntjesapp.data.badge

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Badge

/**
 * Data klasse voor een badge-entiteit in de Teervoetpuntjes applicatie.
 *
 * Deze data klasse vertegenwoordigt een badge in de Room database.
 * Badges worden opgeslagen in een tabel met de naam "badges".
 */
@Entity(tableName = "badges")
data class BadgeEntity(
    /**
     * De unieke identificatie van de badge (primary key).
     *
     * @param id De ID van de badge (standaardwaarde 0).
     */
    @PrimaryKey
    val id: Int = 0,

    /**
     * @param titel De titel van de badge (standaardwaarde "").
     */
    val titel: String = "",

    /**
     * @param image_url De URL van de afbeelding (standaardwaarde "").
     */
    val image_url: String = "",
) {

    companion object {
        /**
         * Converteert een BadgeEntity object naar een Badge object uit het Model package.
         *
         * @param badgeEntity De BadgeEntity die geconverteerd moet worden.
         * @return Badge Het geconverteerde Badge object.
         */
        fun asDomainBadge(badgeEntity: BadgeEntity): Badge {
            return Badge(
                badgeEntity.id,
                badgeEntity.titel,
                badgeEntity.image_url,
            )
        }
    }
}
