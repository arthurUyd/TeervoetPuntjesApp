package com.example.teervoetpuntjesapp.data.puntje

import com.example.teervoetpuntjesapp.Model.Puntje
import kotlinx.coroutines.flow.Flow

/**
 * Interface voor het beheren van puntjes.
 */
interface PuntjesRepository {
    /**
     * Haalt een lijst van alle puntjes op.
     *
     * @return Een Flow van een lijst met Puntje objecten.
     */
    suspend fun getPuntjes(): Flow<List<Puntje>>
}
