package com.example.teervoetpuntjesapp.ui.badge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.teervoetpuntjesapp.Model.Badge
import com.example.teervoetpuntjesapp.TeervoetApplicatie
import com.example.teervoetpuntjesapp.data.badge.BadgeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.IOException

class BadgePaginaViewModel(private val badgeRepository: BadgeRepository) : ViewModel() {

    private val _badge = MutableStateFlow(Badge())
    val badge = _badge.asStateFlow()

    fun getBadge(id: Int) {
        viewModelScope.launch {
            try {
                val g = badgeRepository.getBadge(id)
                _badge.value = g.first()
            } catch (err: IOException) {
                err.printStackTrace()
            }
        }
    }

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
