package com.example.teervoetpuntjesapp.data.puntje

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import kotlinx.serialization.Serializable

/**
 * Deze klasse representeert een PuntjeEntity in de Room database.
 *
 * De klasse heeft een foreign key die gelinkt is aan een badge.
 */
@Entity(
    tableName = "puntjes",
    foreignKeys = [
        ForeignKey(entity = BadgeEntity::class, parentColumns = ["id"], childColumns = ["badge_id"], onDelete = CASCADE, onUpdate = CASCADE),
    ],
    indices = [
        Index(value = ["badge_id"]),
    ],
)
data class PuntjeEntity(
    /**
     * Unieke identificatie van het puntje (primary key).
     */
    @PrimaryKey
    val id: Int = 0,

    /**
     * Titel of beschrijving van het puntje.
     */
    val titel: String = "",

    /**
     * ID van de badge waar dit puntje aan geassocieerd is (foreign key).
     */
    val badge_id: Int = 0,
) {
    companion object {
        /**
         * Converteert een PuntjeEntity object naar een Puntje object uit het Model package.
         *
         * @param puntjeEntity De PuntjeEntity die geconverteerd moet worden.
         * @return Puntje Het geconverteerde PuntjeEntity object.
         */
        fun asDomainPuntje(puntjeEntity: PuntjeEntity): Puntje {
            return Puntje(
                puntjeEntity.id,
                puntjeEntity.titel,
                puntjeEntity.badge_id,
            )
        }
    }
}

/**
 * Data class voor het ophalen van een lijst van puntId's voor een api call.
 */
@Serializable
data class PuntjeGet(
    val punt_id: Int,
)
