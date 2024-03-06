package com.example.teervoetpuntjesapp.data.puntje

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Puntje

@Entity(tableName = "puntjes")
data class PuntjeEntity(
    @PrimaryKey
    val id: Int = 0,
    val titel: String = "",
    val badge_id: Int = 0
)

fun PuntjeEntity.asDomainPuntje(): Puntje {
    return Puntje(
        this.id,
        this.titel,
        this.badge_id
    )
}
fun Puntje.asPuntjeEntity(): PuntjeEntity{
    return PuntjeEntity(
        this.id,
        this.titel,
        this.badge_id
    )
}

