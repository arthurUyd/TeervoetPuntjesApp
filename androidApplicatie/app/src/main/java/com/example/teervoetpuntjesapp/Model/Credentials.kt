package com.example.teervoetpuntjesapp.Model

import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    var email: String = "",
    var password: String = "",
    // var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && email.isNotEmpty()
    }
}
