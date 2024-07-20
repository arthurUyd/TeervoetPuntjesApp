package com.example.teervoetpuntjesapp.data.puntje

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.data.badge.BadgeEntity

@Entity(
    tableName = "puntjes",
    foreignKeys = [
        ForeignKey(entity = BadgeEntity::class, parentColumns = ["id"], childColumns = ["badge_id"], onDelete = CASCADE, onUpdate = CASCADE),
    ],
)
data class PuntjeEntity(
    @PrimaryKey
    val id: Int = 0,
    val titel: String = "",
    val badge_id: Int = 0,
)

fun PuntjeEntity.asDomainPuntje(): Puntje {
    return Puntje(
        this.id,
        this.titel,
        this.badge_id,
    )
}
fun Puntje.asPuntjeEntity(): PuntjeEntity {
    return PuntjeEntity(
        this.id,
        this.titel,
        this.badge_id,
    )
}
