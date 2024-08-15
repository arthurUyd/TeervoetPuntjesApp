package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Gebruiker(
    val id: Int,
    val naam: String,
    val email: String,
    val password: String,
    val isLeider: Int,
)

@Serializable
data class Gebruiker_puntje(

    val id: Int,
    val punt_id: Int,
)
