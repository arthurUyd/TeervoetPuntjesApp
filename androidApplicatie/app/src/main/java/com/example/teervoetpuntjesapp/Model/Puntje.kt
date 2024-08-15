package com.example.teervoetpuntjesapp.Model

import com.example.teervoetpuntjesapp.data.puntje.PuntjeEntity
import kotlinx.serialization.Serializable

@Serializable
data class Puntje(
    val id: Int,
    val titel: String,
    val badge_id: Int,
){
    companion object{
        fun asPuntjeEntity(puntje: Puntje): PuntjeEntity{
            return PuntjeEntity(
                puntje.id,
                puntje.titel,
                puntje.badge_id
            )
        }
    }
}
