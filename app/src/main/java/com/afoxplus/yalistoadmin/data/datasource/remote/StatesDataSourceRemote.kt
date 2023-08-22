package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSource
import com.afoxplus.yalistoadmin.data.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import javax.inject.Inject

class StatesDataSourceRemote @Inject constructor(
    private val api: AdminApiOrdersNetwork
) : StatesDataSource {
    override suspend fun states(): ResultState<List<StatesEntity>> {
        val response = try {
            api.states().body()?.payload!!.map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
