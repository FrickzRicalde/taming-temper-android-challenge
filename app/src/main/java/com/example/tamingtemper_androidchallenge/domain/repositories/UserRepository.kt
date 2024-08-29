package com.example.tamingtemper_androidchallenge.domain.repositories

import androidx.compose.ui.graphics.ImageBitmap
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperFile
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData)

    fun loadTemperLevels(): Flow<TemperLevelsData?>?

    fun loadTemperLevelsFromResource(): TemperLevels

    fun loadImage(temperFile: TemperFile, bitmap: (ImageBitmap) -> Unit)
}