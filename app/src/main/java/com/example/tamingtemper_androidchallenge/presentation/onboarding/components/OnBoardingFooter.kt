package com.example.tamingtemper_androidchallenge.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.example.tamingtemper_androidchallenge.R


@Composable
fun OnBoardingFooter(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.weight(0.05f))
        Image(
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.50f),
            painter = painterResource(id = R.drawable.flag_icon),
            contentDescription = "",
        )
        Spacer(modifier = Modifier.weight(0.05f))
        Text(
            text = "Journey",
            textAlign = TextAlign.Center,
            maxLines = 1,
            color = Color(0xFF6442EF),
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.4f),
        )
    }
}