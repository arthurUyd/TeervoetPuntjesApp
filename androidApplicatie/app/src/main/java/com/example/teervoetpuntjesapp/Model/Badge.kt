package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Badge(
    val id: Int,
    val titel: String,
    val image_url: String
)
