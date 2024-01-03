package com.example.teervoetpuntjesapp

import android.app.Application
import com.example.teervoetpuntjesapp.data.AppContainer
import com.example.teervoetpuntjesapp.data.DefaultAppContainer

class TeervoetApplicatie: Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}