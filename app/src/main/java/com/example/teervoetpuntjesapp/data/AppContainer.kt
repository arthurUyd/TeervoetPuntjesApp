package com.example.teervoetpuntjesapp.data

import com.example.teervoetpuntjesapp.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val badgeRepository: BadgeRepository
    val gebruikerRepository: GebruikerRepository
    val puntjesRepository: PuntjesRepository
}

class DefaultAppContainer : AppContainer {

    private val BASE_URL = "http://10.0.2.2:9000/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
    
    private val badgeRetrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    
    private val puntjesRetrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
    private val gebruikerRetrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val badgeRepository: BadgeRepository by lazy {
        NetworkBadgeRepository(badgeRetrofitService)
    }
    override val puntjesRepository: PuntjesRepository by lazy {
        NetworkPuntjesRepository(puntjesRetrofitService)
    }

    override val gebruikerRepository: GebruikerRepository by lazy {
        NetworkGebruikerRepository(gebruikerRetrofitService)
    }
}
