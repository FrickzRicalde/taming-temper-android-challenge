package com.example.tamingtemper_androidchallenge.domain.usecases

import com.example.tamingtemper_androidchallenge.domain.manager.LocalUserManager

class SaveTemperLevels(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(json: String){
        localUserManager.saveTemperLevels(json)
    }
}