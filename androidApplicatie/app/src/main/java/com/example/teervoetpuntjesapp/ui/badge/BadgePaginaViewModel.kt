package com.example.teervoetpuntjesapp.ui.badge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository

class BadgePaginaViewModel(private val badgeRepository: BadgeRepository) : ViewModel() {



    companion object {
        private var Instance: BadgePaginaViewModel? = null
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                if (Instance == null) {
                    val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TeervoetApplicatie)
                    val badgeRepository = application.container.badgeRepository
                    Instance = BadgePaginaViewModel(badgeRepository = badgeRepository)
                }
                Instance!!
            }
        }
    }
}
