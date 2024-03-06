package com.example.teervoetpuntjesapp.data.gebruiker

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.Gebruiker
import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity

@Entity(tableName = "gebruikers")
data class GebruikerEntity(
    @PrimaryKey
    val id: Int,
    val naam: String,
    val email: String,
    val password: String,
    val isLeider: Int,
)

fun GebruikerEntity.asDomainGebruiker(): Gebruiker{
    return Gebruiker(
        this.id,
        this.naam,
        this.email,
        this.password,
        this.isLeider
    )
}

fun Gebruiker.asGebruikerEntity(): GebruikerEntity{
    return GebruikerEntity(
        this.id,
        this.naam,
        this.email,
        this.password,
        this.isLeider
    )
}

@Entity(
    tableName = "gebruiker_puntje",
    primaryKeys = ["gebruikerId", "puntId"],
    foreignKeys = [
        ForeignKey(entity = GebruikerEntity::class, parentColumns = ["id"], childColumns = ["gebruikerId"], onDelete = CASCADE, onUpdate = CASCADE),
        ForeignKey(entity = PuntjeEntity::class, parentColumns = ["id"], childColumns = ["puntId"], onDelete = CASCADE, onUpdate = CASCADE),
    ],
)
data class Gebruiker_PuntjeEntity(
    val gebruikerId: Int,
    val puntId: Int,
)
