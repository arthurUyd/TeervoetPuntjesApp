package com.example.teervoetpuntjesapp.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teervoetpuntjesapp.Model.puntID

@Entity(tableName = "badge")
data class dbBadge(
    @PrimaryKey
    val id: Int,
    val titel: String,
    val image_url: String
)

@Entity(tableName = "puntjes")
data class dbPuntjes(
    @PrimaryKey
    val id: Int,
    val titel: String,
    val badge_id: Int
)

@Entity(tableName = "gebruiker")
data class dbGebruiker(
    @PrimaryKey
    val id: Int,
    val naam: String,
    val email: String,
    val password: String,
    val isLeider: Int,
    val puntjes: List<puntID>? = null,
)