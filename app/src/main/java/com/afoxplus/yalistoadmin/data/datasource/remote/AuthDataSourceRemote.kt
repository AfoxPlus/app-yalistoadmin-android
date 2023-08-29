package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiRestaurantNetwork
import com.afoxplus.yalistoadmin.data.datasource.AuthRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.model.request.toRequest
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class AuthDataSourceRemote @Inject constructor(
    private val api: AdminApiRestaurantNetwork
) : AuthRemote {

    override suspend fun auth(params: AuthParams): ResultState<Auth> {
        val response = try {
            api.auth(params.toRequest()).body()?.payload!!.toEntity()
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
