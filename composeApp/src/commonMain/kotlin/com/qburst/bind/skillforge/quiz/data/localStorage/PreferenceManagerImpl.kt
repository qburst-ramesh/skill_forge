package com.qburst.bind.skillforge.quiz.data.localStorage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first

class PreferenceManagerImpl(
    private val dataStore: DataStore<Preferences>
) : PreferenceManager {

    private object Keys {
        val KEY_API_ACCESS_TOKEN = stringPreferencesKey("KEY_API_ACCESS_TOKEN")
        val KEY_API_REFRESH_TOKEN = stringPreferencesKey("KEY_API_REFRESH_TOKEN")
        val IS_LOGGED_IN = stringPreferencesKey("IS_LOGGED_IN")
        val COUNT = stringPreferencesKey("COUNT")
    }

    override suspend fun saveAccessToken(token: String?) {
        token?.let {
            dataStore.edit {
                it[Keys.KEY_API_ACCESS_TOKEN] = token
            }
        }
    }

    override suspend fun getAccessToken(): String? {
        return dataStore.data.first()[Keys.KEY_API_ACCESS_TOKEN]
    }

    override suspend fun saveRefreshToken(token: String?) {
        token?.let {
            dataStore.edit {
                it[Keys.KEY_API_REFRESH_TOKEN] = token
            }
        }
    }

    override suspend fun getRefreshToken(): String? {
        return dataStore.data.first()[Keys.KEY_API_REFRESH_TOKEN]
    }

    override suspend fun setUserLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit {
            it[Keys.IS_LOGGED_IN] = isLoggedIn.toString()
        }
    }

    override suspend fun isUserLoggedIn(): Boolean {
        return dataStore.data.first()[Keys.IS_LOGGED_IN].toBoolean()
    }
}