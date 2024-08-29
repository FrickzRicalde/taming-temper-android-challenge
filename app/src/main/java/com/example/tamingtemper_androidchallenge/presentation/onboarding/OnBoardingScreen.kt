package com.example.tamingtemper_androidchallenge.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.models.User
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.OnBoardingFooter
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.OnBoardingHeader
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.OnBoardingPage
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.PageIndicator
import com.example.tamingtemper_androidchallenge.util.Dimens.MediumPadding1
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    user: User?,
    temperLevels: TemperLevels?
) {
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.03f))

        OnBoardingHeader(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.06f),
            user = user
        )

        Spacer(modifier = Modifier.weight(0.03f))

        val temperLevelList = temperLevels?.levels
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
                .weight(0.12f)
                .fillMaxWidth()
                .padding(horizontal = MediumPadding1)
                .navigationBarsPadding(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PageIndicator(
                pageSize = pageSize,
                selectedPage = pagerState.currentPage,
                onClickItem = { index ->
                    coroutineScope.launch {
                        pagerState.scrollToPage(
                            page = index
                        )
                    }
                }
            )
        }

        HorizontalPager(
            modifier = Modifier.weight(0.66f),
            state = pagerState,
        ) { index ->
            OnBoardingPage(temperLevel = temperLevelList[index])

        }

        OnBoardingFooter(
            modifier = Modifier
                .clickable {
                    // Journey Action
                }
                .weight(0.1f)
                .fillMaxSize(),
        )

    }

}