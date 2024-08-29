package com.example.tamingtemper_androidchallenge.domain.datasource

import androidx.compose.ui.graphics.ImageBitmap

interface ImageDatasource {
    fun downloadImage(url: String, success: (Boolean)->Unit)
    fun loadImageFromAppDir(url: String?, imageBitmap: (ImageBitmap)->Unit)
}