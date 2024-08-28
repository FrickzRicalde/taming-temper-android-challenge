package com.example.tamingtemper_androidchallenge.data.models

data class TemperActivityData(
    val id: String?,
    val challengeId: String?,
    val type: String?,
    val title: String?,
    val titleB: String?,
    val description: String?,
    val descriptionB: String?,
    val state: String?,
    val icon: TemperIconData?,
    val lockedIcon: TemperIconData?,
)
