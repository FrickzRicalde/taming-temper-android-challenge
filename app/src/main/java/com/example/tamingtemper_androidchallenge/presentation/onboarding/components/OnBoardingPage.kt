package com.example.tamingtemper_androidchallenge.presentation.onboarding.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevel
import com.example.tamingtemper_androidchallenge.util.Dimens.MediumPadding1
import com.example.tamingtemper_androidchallenge.presentation.onboarding.Page

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    temperLevel: TemperLevel,
) {
    Column(modifier = modifier) {


//        AsyncImage(
//            model = "//assets.ctfassets.net/37k4ti9zbz4t/DVQrkzmSp53EXqmFn9z1L/f4270b3b29c508c04493ead947e8651f/Chapter_01__Lesson_02__State_Active.pdf",
//            contentDescription = "Translated description of what the image contains"
//        )

//        Image(
//            modifier = Modifier
//                .fillMaxWidth()
//                .fillMaxHeight(fraction = 0.7f),
////            painter = painterResource(id = page.image),
//            painter = painter,
//            contentDescription = ""
//        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(text = temperLevel.title?:"", modifier = Modifier.padding(MediumPadding1))
        Text(text = temperLevel.description?:"", modifier = Modifier.padding(MediumPadding1))

    }
}