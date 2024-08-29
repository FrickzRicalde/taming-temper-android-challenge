package com.example.tamingtemper_androidchallenge.presentation.onboarding

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.tamingtemper_androidchallenge.R
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.OnBoardingPage
import com.example.tamingtemper_androidchallenge.presentation.onboarding.components.PageIndicator
import com.example.tamingtemper_androidchallenge.util.Dimens.MediumPadding1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(
    temperLevels: TemperLevels
) {
    var currentProgress by remember { mutableFloatStateOf(0.3f) }


    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.03f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.06f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(0.05f))
            Image(
                modifier = Modifier
                    .weight(0.15f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.journey_icon),
                contentDescription = "",
            )

            Spacer(modifier = Modifier.weight(0.01f))

            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight(),
            ) {
                Text(
                    text = "Taming Temper",
                    textAlign = TextAlign.Center,
                    maxLines = 1, modifier = Modifier.weight(0.5f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LinearProgressIndicator(
                        progress = { currentProgress },
                        color = Color(0xFF6442EF),
                        modifier = Modifier.weight(0.55f),
                    )
                    Text(
                        text = "30%",
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        color = Color(0xFF6442EF),
                        modifier = Modifier
                            .weight(0.3f)
                            .fillMaxHeight(),
                    )
                    Spacer(
                        modifier = Modifier.weight(0.15f),
                    )
                }

            }

            Spacer(modifier = Modifier.weight(0.09f))

            Image(
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.fire_icon),
                contentDescription = "",
            )
            Text(text = "0", modifier = Modifier.weight(0.05f))
            Image(
                modifier = Modifier
                    .weight(0.10f)
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "",
            )
            Spacer(modifier = Modifier.weight(0.05f))


        }

        Spacer(modifier = Modifier.weight(0.03f))

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
                .weight(0.12f)
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
//        Spacer(modifier = Modifier.weight(1f))

        HorizontalPager(
            modifier = Modifier.weight(0.76f),
            state = pagerState,
        ) { index ->
            OnBoardingPage(temperLevel = temperLevelList[index])

        }


    }

}