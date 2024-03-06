package com.example.teervoetpuntjesapp.data.puntje

import com.example.teervoetpuntjesapp.Model.Puntje
import kotlinx.coroutines.flow.Flow

interface PuntjesRepository {
    suspend fun getPuntjes(): Flow<List<Puntje>>
}

