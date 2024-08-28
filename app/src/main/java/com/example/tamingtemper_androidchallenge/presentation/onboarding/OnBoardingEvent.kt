package com.example.tamingtemper_androidchallenge.presentation.onboarding

import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData

sealed class OnBoardingEvent {
    class SaveTemperLevels(
        val temperLevels: TemperLevelsData
    ) : OnBoardingEvent()

    class LoadTemperLevels : OnBoardingEvent()
}