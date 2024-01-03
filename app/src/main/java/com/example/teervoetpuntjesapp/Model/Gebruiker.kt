package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Gebruiker(
    val id: Int,
    val naam: String,
    val email: String,
    val password: String,

    val isLeider: Int,
    val puntjes: List<puntID>? = null,
)

@Serializable
data class puntID(
    val punt_id: Int
)