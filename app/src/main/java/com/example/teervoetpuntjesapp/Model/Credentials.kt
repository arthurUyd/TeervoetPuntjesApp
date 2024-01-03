package com.example.teervoetpuntjesapp.Model

data class Credentials(
    var login: String = "",
    var pwd: String = "",
    // var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}
