package com.example.tamingtemper_androidchallenge.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.tamingtemper_androidchallenge.domain.manager.LocalUserManager
import com.example.tamingtemper_androidchallenge.util.Constants
import com.example.tamingtemper_androidchallenge.util.Constants.USER_PREFERENCE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCE)

private object PreferenceKeys {
    val TEMPER_LEVELS = stringPreferencesKey(name = Constants.TEMPER_LEVELS)
}
class LocalUserManagerImpl(
    private val context: Context
): LocalUserManager {
    override suspend fun saveTemperLevels(json: String) {
        context.dataStore.edit { settings ->
            settings[PreferenceKeys.TEMPER_LEVELS] =json

        }
    }

    override fun loadTemperLevels(): Flow<String> {
        return context.dataStore.data.map {preferences ->
            preferences[PreferenceKeys.TEMPER_LEVELS]?:""

        }
    }
}

//private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "")