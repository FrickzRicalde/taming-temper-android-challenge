package com.example.tamingtemper_androidchallenge.presentation.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PageIndicator(
    modifier: Modifier = Modifier,
    pageSize: Int,
    selectedPage: Int,
    selectedColor: Color = Color.Blue,
    unselectedColor: Color = Color.Gray,
    onClickItem: (Int) -> Unit,
) {
    val days = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        repeat(pageSize) { page ->
            Column(
                modifier = modifier
                    .weight(1f)
                    .clickable {
                        onClickItem(page)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(color = if (page == selectedPage) selectedColor else unselectedColor)
                )

                Text(
                    text = days[page]
                )

                if (page == selectedPage) {
                    HorizontalDivider(thickness = 3.dp, color = selectedColor)
                }

            }
        }
    }
}