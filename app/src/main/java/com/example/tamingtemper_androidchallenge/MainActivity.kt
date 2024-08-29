package com.example.tamingtemper_androidchallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tamingtemper_androidchallenge.presentation.onboarding.OnBoardingViewModel
import com.example.tamingtemper_androidchallenge.presentation.onboarding.OnboardingScreen
import com.example.tamingtemper_androidchallenge.ui.theme.TamingTemperAndroidChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            TamingTemperAndroidChallengeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val viewModel: OnBoardingViewModel = hiltViewModel()
                    val temperLevels by viewModel.temperLevels.collectAsState()
                    viewModel.loadTemperLevels()

                    val user by viewModel.user.collectAsState()
                    viewModel.loadUser()

                    Box(modifier = Modifier.padding(innerPadding)) {
                        OnboardingScreen(user = user, temperLevels = temperLevels)
                    }
                }
            }
        }
    }

}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    TamingTemperAndroidChallengeTheme {
//        Greeting("Android")
//    }
//}
