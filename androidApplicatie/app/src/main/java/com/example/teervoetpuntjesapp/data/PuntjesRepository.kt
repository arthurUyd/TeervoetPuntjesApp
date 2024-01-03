package com.example.teervoetpuntjesapp.data

import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.network.ApiService

interface PuntjesRepository {
    suspend fun getPuntjes(): List<Puntje>
}

class NetworkPuntjesRepository(
    val puntjesApiService: ApiService,
) : PuntjesRepository {
    override suspend fun getPuntjes(): List<Puntje> = puntjesApiService.getPuntjes()
}
