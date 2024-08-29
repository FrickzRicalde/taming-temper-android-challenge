package com.example.tamingtemper_androidchallenge.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tamingtemper_androidchallenge.R
import com.example.tamingtemper_androidchallenge.domain.models.User

@Composable
fun OnBoardingHeader(
    modifier: Modifier = Modifier,
    user: User?,
) {
    Row(
        modifier = modifier,
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
                text = user?.name ?: "Taming Temper",
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
                    progress = { user?.progress ?: 0.3f },
                    color = Color(0xFF6442EF),
                    modifier = Modifier.weight(0.55f),
                )
                Text(
                    text = "${user?.progress?.times(100)?.toInt() ?: 30}%",
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
        Text(
            text = "${user?.fireAmount ?: 0}",
            color = Color(0xFF6442EF),
            maxLines = 1,
            modifier = Modifier.weight(0.05f)
        )
        Box(
            modifier = Modifier
                .weight(0.10f)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxHeight(),
                painter = painterResource(id = R.drawable.profile_icon),
                contentScale = ContentScale.FillHeight,
                contentDescription = "",
            )

            Image(
                modifier = Modifier
                    .fillMaxHeight(0.4f).offset(y = (-2).dp),
                painter = painterResource(id = R.drawable.profile_person_icon),
                contentScale = ContentScale.FillHeight,
                contentDescription = "",
            )
        }

        Spacer(modifier = Modifier.weight(0.05f))


    }
}