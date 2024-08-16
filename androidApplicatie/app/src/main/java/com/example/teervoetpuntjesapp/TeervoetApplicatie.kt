package com.example.teervoetpuntjesapp

import android.app.Application
import com.example.teervoetpuntjesapp.data.AppContainer
import com.example.teervoetpuntjesapp.data.DefaultAppContainer

/**
 * Applicatieklasse voor Teervoetjespuntjes applicatie.
 *
 * Deze applicatieklasse is verantwoordelijk voor de initialisatie van de dependency injection container.
 * De container beheert de instanties van verschillende data klassen en repositories die in de applicatie worden gebruikt.
 */
class TeervoetApplicatie : Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
