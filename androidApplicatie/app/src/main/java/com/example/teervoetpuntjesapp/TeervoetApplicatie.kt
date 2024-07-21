package com.example.teervoetpuntjesapp

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.teervoetpuntjesapp.data.AppContainer
import com.example.teervoetpuntjesapp.data.DefaultAppContainer
import com.example.teervoetpuntjesapp.data.gebruiker.UserContext

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting",
)
class TeervoetApplicatie : Application() {

    lateinit var container: AppContainer
    lateinit var userContext: UserContext

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
        userContext = UserContext(dataStore)
    }
}
