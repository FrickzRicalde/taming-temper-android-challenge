package com.example.tamingtemper_androidchallenge.domain.repositories

import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData)

    fun loadTemperLevels(): Flow<TemperLevels?>

    fun loadTemperLevelsFromResource(): TemperLevels
}