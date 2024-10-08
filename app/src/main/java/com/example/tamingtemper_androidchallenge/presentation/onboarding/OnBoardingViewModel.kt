package com.example.tamingtemper_androidchallenge.presentation.onboarding

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperFile
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.models.User
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private var _temperLevels = MutableStateFlow<TemperLevels?>(null)
    val temperLevels = _temperLevels.asStateFlow()

    private var _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    fun saveTemperLevels(temperLevelsData: TemperLevelsData) {
        viewModelScope.launch {
            userRepository.saveTemperLevels(temperLevelsData)
        }

    }

    fun loadTemperLevels() {
        viewModelScope.launch {
            userRepository.loadTemperLevels()?.collect {

                if (it == null || it.levels.isNullOrEmpty()) {
                    var levels = userRepository.loadTemperLevelsFromResource()
                    _temperLevels.value = levels
                    saveTemperLevels(levels.toData())
                } else {
                    _temperLevels.value = TemperLevels().fromData(it)
                }
            }
        }

    }


    fun loadImage(file: TemperFile, imageBitmap: (ImageBitmap) -> Unit) {
        viewModelScope.launch {
            userRepository.loadImage(file, imageBitmap)
        }
    }

    fun loadUser(){
        _user.value =  User("101", "Taming Temper", 0.45f, 5)
    }
}