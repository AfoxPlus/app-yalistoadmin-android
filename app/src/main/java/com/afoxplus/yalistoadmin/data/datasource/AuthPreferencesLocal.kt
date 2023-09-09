package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.domain.entities.Auth

interface AuthPreferencesLocal {
    suspend fun getAuth(): Auth
    suspend fun saveAuth(auth: Auth)
}
