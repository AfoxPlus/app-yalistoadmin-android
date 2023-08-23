package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams

interface AuthRepository {
    suspend fun auth(params: AuthParams): ResultState<AuthEntity>
}
