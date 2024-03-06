package com.example.teervoetpuntjesapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable

data class Gebruiker(
    val id: Int,
    val naam: String,
    val email: String,
    val password: String,
    val isLeider: Int,
    //  val puntjes: List<Int>? = null,
)

@Serializable
data class Gebruiker_puntje(

    val id: Int,
    val punt_id: Int,
)
