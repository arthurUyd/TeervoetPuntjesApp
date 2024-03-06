package com.example.teervoetpuntjesapp.data

import android.content.Context
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import com.example.teervoetpuntjesapp.data.badge.OfflineFirstBadgeRepository
import com.example.teervoetpuntjesapp.data.gebruiker.GebruikerRepository
import com.example.teervoetpuntjesapp.data.gebruiker.NetworkGebruikerRepository
import com.example.teervoetpuntjesapp.data.gebruiker.OfflineFirstGebruikerRepository
import com.example.teervoetpuntjesapp.data.puntje.OfflineFirstPuntjesRepository
import com.example.teervoetpuntjesapp.data.puntje.PuntjesRepository
import com.example.teervoetpuntjesapp.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {

    val gebruikerNetworkRepository: GebruikerRepository
    val badgeRepository: BadgeRepository
    val puntjesRepository: PuntjesRepository
    val gebruikerLocalRepository: GebruikerRepository
}

class DefaultAppContainer(
    private val context: Context,
) : AppContainer {

    private val BASE_URL = "http://10.0.2.2:9000/api/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()

    private val teervoetRetrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    override val gebruikerNetworkRepository: GebruikerRepository by lazy {
        NetworkGebruikerRepository(teervoetRetrofitService)
    }

    override val badgeRepository: BadgeRepository by lazy {
        OfflineFirstBadgeRepository(TeervoetAppDatabase.getDatabase(context).badgeDao(),teervoetRetrofitService)
    }
    override val puntjesRepository: PuntjesRepository by lazy {
        OfflineFirstPuntjesRepository(TeervoetAppDatabase.getDatabase(context).puntjeDao(), teervoetRetrofitService)
    }
    override val gebruikerLocalRepository: GebruikerRepository by lazy {
        OfflineFirstGebruikerRepository(TeervoetAppDatabase.getDatabase(context).gebruikerDao())
    }
}
