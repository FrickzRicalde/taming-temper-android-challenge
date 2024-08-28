package com.example.tamingtemper_androidchallenge.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.util.Dimens.MediumPadding1
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.OnBoardingPage
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.PageIndicator

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    temperLevels: TemperLevels
) {
    Column(modifier = Modifier.fillMaxSize()) {

        val temperLevelList = temperLevels.levels

        if (temperLevelList.isNullOrEmpty()) {
            return
        }

        val pageSize = temperLevelList.size ?: 0
        if (pageSize <= 0) {
            return
        }


        val pagerState = rememberPagerState(initialPage = 0) {
            pageSize
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PageIndicator(
//                modifier = Modifier.width(52dp),
                pageSize = pageSize,
                selectedPage = pagerState.currentPage
            )

        }
        Spacer(modifier = Modifier.weight(1f))

        HorizontalPager(state = pagerState) { index ->
            OnBoardingPage(temperLevel = temperLevelList[index])

        }


    }

}