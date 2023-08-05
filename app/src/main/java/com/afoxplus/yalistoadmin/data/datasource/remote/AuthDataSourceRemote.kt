package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiNetwork
import com.afoxplus.yalistoadmin.data.datasource.AuthDataSource
import com.afoxplus.yalistoadmin.data.model.request.toRequest
import com.afoxplus.yalistoadmin.data.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class AuthDataSourceRemote @Inject constructor(
    private val api: AdminApiNetwork
) : AuthDataSource {

    override suspend fun auth(params: AuthParams): ResultState<AuthEntity> {
        val response = try {
            api.auth(params.toRequest()).body()?.payload!!.toEntity()
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }

}