package com.example.tamingtemper_androidchallenge.domain.repositories

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperFile
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData)

    fun loadTemperLevels(): Flow<TemperLevels?>

    fun loadTemperLevelsFromResource(): TemperLevels

    fun loadImage(file: TemperFile, bitmap: (ImageBitmap)-> Unit)
}