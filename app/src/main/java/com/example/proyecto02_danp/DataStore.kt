package com.example.proyecto02_danp

import android.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class DataStore(
    private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>
) {
    suspend fun saveBackground(noteBackgroundColor: String) {
        dataStore.edit { preferences ->
            preferences[BACKGROUND_COLOR] = noteBackgroundColor
        }
    }
    suspend fun saveSize(size_text: String) {
        dataStore.edit { preferences ->
            preferences[SIZE_TEXT] = size_text
        }
    }
    suspend fun saveFont(size_text: String) {
        dataStore.edit { preferences ->
            preferences[SIZE_TEXT] = size_text
        }
    }
    suspend fun saveidUSER(saveIDUser: String) {
        dataStore.edit { preferences ->
            preferences[SESSION_ID_USER] = saveIDUser
        }
    }

    suspend fun deleteidUSER() {
        dataStore.edit { preferences ->
            preferences[SESSION_ID_USER] = ""
        }
    }

    val idUserT: Flow<String>
        get() = dataStore.data.map { preferences ->
            preferences[SESSION_ID_USER] ?: ""
        }

    val backgroundColor: Flow<String>
        get() = dataStore.data.map { preferences ->
            preferences[BACKGROUND_COLOR] ?: Color.YELLOW.toString()
        }

    val sizeText: Flow<String>
        get() = dataStore.data.map { sizeT ->
            sizeT[SIZE_TEXT] ?: "15"
        }

    val fontT: Flow<String>
        get() = dataStore.data.map { fontTi ->
            fontTi[SIZE_TEXT] ?: "15"
        }

    companion object {
        const val PREFS_NAME = "PREFS_NAME"
        private val BACKGROUND_COLOR = stringPreferencesKey("key_app_background_color")
        private val SESSION_ID_USER = stringPreferencesKey("session_text_la")
        private val SIZE_TEXT = stringPreferencesKey("size_text_la")
        private val FONT_TEXT = stringPreferencesKey("font_text_la")
    }
}
