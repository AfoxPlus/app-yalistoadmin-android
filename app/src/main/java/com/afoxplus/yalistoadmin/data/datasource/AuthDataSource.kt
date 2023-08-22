package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams

interface AuthDataSource {
    suspend fun auth(params: AuthParams): ResultState<AuthEntity>
}
