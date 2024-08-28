package com.example.tamingtemper_androidchallenge.data.repositories

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.tamingtemper_androidchallenge.data.datasource.LocalDatasource
import com.example.tamingtemper_androidchallenge.data.manager.dataStore
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.models.TemperLevels
import com.example.tamingtemper_androidchallenge.domain.repositories.UserRepository
import com.example.tamingtemper_androidchallenge.util.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

//val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE)
//
private object PreferenceKeys2 {
    val TEMPER_LEVELS = stringPreferencesKey(name = Constants.TEMPER_LEVELS)
}

class UserRepositoryImpl(
    private val context: Context,
    private val localDatasource: LocalDatasource,
) : UserRepository {

    override suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData) {

        val json = Gson().toJson(temperLevelsData)
        context.dataStore.edit { settings ->
            settings[PreferenceKeys2.TEMPER_LEVELS] = json

        }
    }

    override fun loadTemperLevels(): Flow<TemperLevels?> {
        return context.dataStore.data.map { preferences ->
            val json = preferences[PreferenceKeys2.TEMPER_LEVELS]
            if (json != null) {
                val temperLevels: TemperLevels = Gson().fromJson(json, object : TypeToken<TemperLevels>() {}.type)
                temperLevels
            }else {
                null
            }
        }
    }

    override fun loadTemperLevelsFromResource(): TemperLevels {

        val fileContent = this::class.java.classLoader?.getResource("response.json")!!.readText()

        println("TEMPER fileContent: $fileContent")

        return Gson().fromJson(fileContent, object : TypeToken<TemperLevels>() {}.type)
    }
}