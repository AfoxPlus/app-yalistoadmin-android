package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams

fun interface AuthRemote {
    suspend fun auth(params: AuthParams): ResultState<Auth>
}
