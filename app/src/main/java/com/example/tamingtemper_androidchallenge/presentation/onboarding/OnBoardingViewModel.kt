package com.example.tamingtemper_androidchallenge.presentation.onboarding

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperFile
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private var _temperLevels = MutableStateFlow(TemperLevels())
    val temperLevels = _temperLevels.asStateFlow()

    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            is OnBoardingEvent.SaveTemperLevels -> {
                saveTemperLevels(event.temperLevels)
            }

            is OnBoardingEvent.LoadTemperLevels -> {
                loadTemperLevels()
            }
        }

    }

    public fun saveTemperLevels(temperLevelsData: TemperLevelsData) {
        viewModelScope.launch {
            userRepository.saveTemperLevels(temperLevelsData)
        }

    }

    public fun loadTemperLevels() {
        viewModelScope.launch {
            userRepository.loadTemperLevels().collect{

                if (it == null || it.levels.isNullOrEmpty()){
                    _temperLevels.value = userRepository.loadTemperLevelsFromResource()
                }
            }
        }

    }


    fun loadImage(file: TemperFile, imageBitmap: (ImageBitmap)->Unit) {
        viewModelScope.launch {
            userRepository.loadImage(file, imageBitmap)
        }
    }
}