package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSourceRemote
import com.afoxplus.yalistoadmin.data.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import javax.inject.Inject

class StatesDataSourceRemoteRemote @Inject constructor(
    private val api: AdminApiOrdersNetwork
) : StatesDataSourceRemote {
    override suspend fun getStates(): ResultState<List<StatesEntity>> {
        val response = try {
            api.getStates().body()?.payload!!.map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
