package com.example.tamingtemper_androidchallenge.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun saveTemperLevels(json: String)

    fun loadTemperLevels(): Flow<String>
}