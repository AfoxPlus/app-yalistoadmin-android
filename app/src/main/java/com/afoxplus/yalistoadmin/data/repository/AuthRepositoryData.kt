package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.AuthDataSource
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class AuthRepositoryData @Inject constructor(
    private val dataSource: AuthDataSource
) : AuthRepository {

    override suspend fun auth(params: AuthParams): ResultState<AuthEntity> {
        return dataSource.auth(params)
    }
}
