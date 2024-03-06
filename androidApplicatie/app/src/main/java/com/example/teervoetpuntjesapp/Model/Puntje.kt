package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Puntje(
    val id: Int,
    val titel: String,
    val badge_id: Int,
)
