package com.example.tamingtemper_androidchallenge.data.datasource

import android.content.Context
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.datasource.RemoteDatasource
import kotlinx.coroutines.flow.Flow

class RemoteDatasourceImpl(
    private val context: Context
): RemoteDatasource {

    override fun getTemperLevels(): Flow<TemperLevelsData?>? {
        return null
    }

}