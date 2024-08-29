package com.example.tamingtemper_androidchallenge.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tamingtemper_androidchallenge.domain.models.TemperAct
import com.example.tamingtemper_androidchallenge.presentation.onboarding.OnBoardingViewModel

@Composable
fun OnBoardingPagItem(
    modifier: Modifier = Modifier,
    temperAct: TemperAct,
    available: Boolean,
) {

    var document by remember { mutableStateOf<ImageBitmap?>(null) }

    val viewModel: OnBoardingViewModel = hiltViewModel()

    val icon = if (available) temperAct.icon else temperAct.lockedIcon

    if (document == null) {
        println("LOAD IMAGEEEEEE")
        icon?.file?.let { file ->
            viewModel.loadImage(file, imageBitmap = { bitmap ->
                document = bitmap
            })
        }
    }


    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        if (document != null) {
            println("RECOMPOSEEEEEEE")

            Image(
                bitmap = document!!,
                contentScale = ContentScale.FillHeight,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(100.dp),
            )
        }


        Spacer(modifier = Modifier.height(5.dp))

        temperAct.title?.let {
            Text(
                text = it,
                fontSize = 12.sp,
                lineHeight = 15.sp,
                textAlign = TextAlign.Center,
                maxLines = 2, modifier = Modifier.fillMaxHeight()
            )
        }

        Spacer(modifier = Modifier.height(15.dp))
    }
}