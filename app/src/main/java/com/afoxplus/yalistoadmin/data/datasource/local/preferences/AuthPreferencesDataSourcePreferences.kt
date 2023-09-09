package com.afoxplus.yalistoadmin.data.datasource.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.afoxplus.yalistoadmin.data.datasource.AuthPreferencesLocal
import com.afoxplus.yalistoadmin.domain.entities.Auth
import javax.inject.Inject
import kotlinx.coroutines.flow.first

class AuthPreferencesDataSourcePreferences @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AuthPreferencesLocal {

    companion object {
        const val PREFERENCES_STRING_CODE = "code"
        const val PREFERENCES_STRING_IMAGE = "urlImageLogo"
        const val PREFERENCES_STRING_KEY = "key"
        const val PREFERENCES_STRING_NAME = "name"
    }

    private val code = stringPreferencesKey(PREFERENCES_STRING_CODE)
    private val image = stringPreferencesKey(PREFERENCES_STRING_IMAGE)
    private val key = stringPreferencesKey(PREFERENCES_STRING_KEY)
    private val name = stringPreferencesKey(PREFERENCES_STRING_NAME)

    override suspend fun getAuth(): Auth {
        return Auth(
            code = dataStore.data.first()[code] ?: "",
            urlImageLogo = dataStore.data.first()[image] ?: "",
            key = dataStore.data.first()[key] ?: "",
            name = dataStore.data.first()[name] ?: ""
        )
    }

    override suspend fun saveAuth(auth: Auth) {
        dataStore.edit { preferences ->
            preferences[code] = auth.code
            preferences[image] = auth.urlImageLogo
            preferences[key] = auth.key
            preferences[name] = auth.name
        }
    }
}
