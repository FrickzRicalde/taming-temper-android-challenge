package com.example.tamingtemper_androidchallenge.data.models

data class TemperLevelData(
    val level: String?,
    val title: String?,
    val description: String?,
    val state: String?,
    val activities: ArrayList<TemperActivityData>?
)
