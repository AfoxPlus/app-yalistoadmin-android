package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.AuthRemote
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class AuthRepositoryData @Inject constructor(
    private val dataSource: AuthRemote
) : AuthRepository {

    override suspend fun auth(params: AuthParams): ResultState<AuthEntity> {
        return dataSource.auth(params)
    }
}
