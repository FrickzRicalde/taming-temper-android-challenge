package com.example.tamingtemper_androidchallenge.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tamingtemper_androidchallenge.data.models.TemperLevelsData
import com.example.tamingtemper_androidchallenge.domain.datasource.LocalDatasource
import com.example.tamingtemper_androidchallenge.util.Constants
import com.example.tamingtemper_androidchallenge.util.Constants.USER_PREFERENCE
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE)

private object PreferenceKeys {
    val TEMPER_LEVELS = stringPreferencesKey(name = Constants.TEMPER_LEVELS)
}

class LocalDatasourceImpl(
    private val context: Context
): LocalDatasource {

    override suspend fun saveTemperLevels(temperLevelsData: TemperLevelsData) {
        val json = Gson().toJson(temperLevelsData)
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.TEMPER_LEVELS] = json

        }
    }

    override fun getTemperLevels(): Flow<TemperLevelsData?>? {
        return context.dataStore.data.map { preferences ->
            val json = preferences[PreferenceKeys.TEMPER_LEVELS]
            if (json != null) {
                val temperLevels: TemperLevelsData =
                    Gson().fromJson(json, object : TypeToken<TemperLevelsData>() {}.type)
                temperLevels
            } else {
                null
            }
        }
    }
}