package com.example.tamingtemper_androidchallenge.domain.models

data class User(
    var id: String? = "101",
    var name: String? = "Taming Temper",
    var progress: Float? = 0.3f,
    var fireAmount: Int? = 0,
)
