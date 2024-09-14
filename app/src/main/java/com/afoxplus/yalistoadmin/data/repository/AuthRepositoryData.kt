package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.AuthPreferencesLocal
import com.afoxplus.yalistoadmin.data.datasource.AuthRemote
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class AuthRepositoryData @Inject constructor(
    private val dataSourceRemote: AuthRemote,
    private val dataSourcePreferences: AuthPreferencesLocal
) : AuthRepository {

    override suspend fun auth(params: AuthParams): ResultState<Auth> {
        return dataSourceRemote.auth(params = params)
    }

    override suspend fun getAuth(): Auth {
        return dataSourcePreferences.getAuth()
    }

    override suspend fun saveAuth(auth: Auth) {
        dataSourcePreferences.saveAuth(auth = auth)
    }
}
