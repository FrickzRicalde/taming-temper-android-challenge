package com.example.tamingtemper_androidchallenge.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tamingtemper_androidchallenge.R
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevel

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    temperLevel: TemperLevel,
) {


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val list = temperLevel.activities
        if (list.isNullOrEmpty()) {
            return
        }

        Box(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.activity_icon),
                contentDescription = "",
            )

            Box(
                Modifier
                    .matchParentSize()
                    .offset(y = 1.dp),
                contentAlignment = Alignment.BottomCenter,
            ) {
                Text(
                    text = "Level: ${temperLevel.level}",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .wrapContentSize()
                        .background(color = Color.Black)
                )
            }
        }



        Text(
            text = temperLevel.title ?: "",
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.05f)
        )

        Text(
            text = temperLevel.description ?: "",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 15.sp,
            maxLines = 2,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .weight(0.06f)
        )

        Spacer(modifier = Modifier.weight(0.01f))

        val isAvailable = temperLevel.state?.uppercase() == "AVAILABLE"

        val state = rememberLazyGridState()
        LazyVerticalGrid(
            state = state,
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .weight(0.68f),
        ) {
            items(list.size, span = {
                if (list.size % 2 != 0) {
                    val value = if (list.lastIndex == it) 2 else 1
                    GridItemSpan(value)
                } else {
                    GridItemSpan(1)
                }
            }) { index ->
                OnBoardingPagItem(temperAct = list[index], available = isAvailable)
            }
        }

    }
}