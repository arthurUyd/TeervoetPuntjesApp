package com.example.teervoetpuntjesapp.data.puntje

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity
import kotlinx.serialization.Serializable

@Entity(
    tableName = "puntjes",
    foreignKeys = [
        ForeignKey(entity = BadgeEntity::class, parentColumns = ["id"], childColumns = ["badge_id"], onDelete = CASCADE, onUpdate = CASCADE),
    ],
    indices = [ // Add indices section
        Index(value = ["badge_id"]), // Index on badge_id column
    ],
)
data class PuntjeEntity(
    @PrimaryKey
    val id: Int = 0,
    val titel: String = "",
    val badge_id: Int = 0,
) {
    companion object {
        fun asDomainPuntje(puntjeEntity: PuntjeEntity): Puntje {
            return Puntje(
                puntjeEntity.id,
                puntjeEntity.titel,
                puntjeEntity.badge_id,
            )
        }
    }
}

@Serializable
data class PuntjeGet(
    val punt_id: Int
)