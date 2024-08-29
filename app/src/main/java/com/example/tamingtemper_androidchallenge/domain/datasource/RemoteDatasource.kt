package com.example.tamingtemper_androidchallenge.domain.datasource

import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    fun getTemperLevels(): Flow<TemperLevelsData?>?
}