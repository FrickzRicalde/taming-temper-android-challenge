package com.example.tamingtemper_androidchallenge.data.repositories

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.datasource.ImageDatasource
import com.example.tamingtemper_androidchallenge.domain.datasource.LocalDatasource
import com.example.tamingtemper_androidchallenge.domain.datasource.RemoteDatasource
import com.example.tamingtemper_androidchallenge.domain.models.TemperFile
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import com.example.tamingtemper_androidchallenge.util.Constants.RESPONSE_JSON
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import java.io.File


class UserRepositoryImpl(
    private val context: Context,
    private val imageDatasource: ImageDatasource,
    private val localDatasource: LocalDatasource,
    private val remoteDatasource: RemoteDatasource,
) : UserRepository {

    override suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData) {
        localDatasource.saveTemperLevels(temperLevelsData)
    }

    override fun loadTemperLevels(): Flow<TemperLevelsData?>? {
        var flow = remoteDatasource.getTemperLevels()
        if (flow == null) {
            flow = localDatasource.getTemperLevels()
        }
        return flow
    }

    override fun loadTemperLevelsFromResource(): TemperLevels {
        val fileContent = this::class.java.classLoader?.getResource(RESPONSE_JSON)!!.readText()
        return Gson().fromJson(fileContent, object : TypeToken<TemperLevels>() {}.type)
    }

    override fun loadImage(temperFile: TemperFile, bitmap: (ImageBitmap) -> Unit) {
        val url = temperFile.url
        val filename = url?.split("/")?.last()

        val file = filename?.let { File(context.filesDir, it) }
        if (file == null) {
            return
        }
        if (file.exists()) {

            imageDatasource.loadImageFromAppDir(url, imageBitmap = {
                bitmap(it)
            })

        } else {
            imageDatasource.downloadImage("https:$url", success = {
                imageDatasource.loadImageFromAppDir(url, imageBitmap = {
                    bitmap(it)
                })
            })
        }


    }
}