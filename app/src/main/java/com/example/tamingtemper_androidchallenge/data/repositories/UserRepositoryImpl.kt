package com.example.tamingtemper_androidchallenge.data.repositories

import android.content.Context
import androidx.compose.ui.graphics.ImageBitmap
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tamingtemper_androidchallenge.data.datasource.Datasource
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperFile
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import com.example.tamingtemper_androidchallenge.util.Constants
import com.example.tamingtemper_androidchallenge.util.Constants.USER_PREFERENCE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE)

private object PreferenceKeys {
    val TEMPER_LEVELS = stringPreferencesKey(name = Constants.TEMPER_LEVELS)
}

class UserRepositoryImpl(
    private val context: Context,
    private val datasource: Datasource,
) : UserRepository {

    override suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData) {

        val json = Gson().toJson(temperLevelsData)
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.TEMPER_LEVELS] = json

        }
    }

    override fun loadTemperLevels(): Flow<TemperLevels?> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[PreferenceKeys.TEMPER_LEVELS]
            if (json != null) {
                val temperLevels: TemperLevels =
                    Gson().fromJson(json, object : TypeToken<TemperLevels>() {}.type)
                temperLevels
            } else {
                null
            }
        }
    }

    override fun loadTemperLevelsFromResource(): TemperLevels {

        val fileContent = this::class.java.classLoader?.getResource("response.json")!!.readText()

        println("TEMPER fileContent: $fileContent")

        return Gson().fromJson(fileContent, object : TypeToken<TemperLevels>() {}.type)
    }

    override fun loadImage(file: TemperFile, bitmap: (ImageBitmap) -> Unit) {
        val url = file.url
        val filename = url?.split("/")?.last()

        val file = File(context.filesDir, filename)

        if (file.exists()) {

            datasource.loadImageFromAppDir(url, imageBitmap = {
                bitmap(it)
            })

        } else {
            datasource.downloadImage("https:$url", success = {
                datasource.loadImageFromAppDir(url, imageBitmap = {
                    bitmap(it)
                })
            })
        }


    }
}