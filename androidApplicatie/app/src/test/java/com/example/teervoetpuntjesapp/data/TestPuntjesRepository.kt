package com.example.teervoetpuntjesapp.data

import com.example.teervoetpuntjesapp.Model.Puntje
import com.example.teervoetpuntjesapp.data.puntje.PuntjesRepository
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestPuntjesRepository() : PuntjesRepository {

    private val puntjesFlow: MutableSharedFlow<List<Puntje>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getPuntjes(): Flow<List<Puntje>> = puntjesFlow
    fun sendPuntjes(puntjes: List<Puntje>) = puntjesFlow.tryEmit(puntjes)
}
