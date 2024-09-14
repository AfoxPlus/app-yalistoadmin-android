package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun auth(params: AuthParams): ResultState<Auth> {
        return repository.auth(params)
    }

    suspend fun authPreferences(): Auth {
        return repository.getAuth()
    }
}
