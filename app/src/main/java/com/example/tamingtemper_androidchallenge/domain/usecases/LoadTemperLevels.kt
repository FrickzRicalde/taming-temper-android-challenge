package com.example.tamingtemper_androidchallenge.domain.usecases

import com.example.tamingtemper_androidchallenge.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class LoadTemperLevels (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke() : Flow<String>{
        return localUserManager.loadTemperLevels()
    }
}