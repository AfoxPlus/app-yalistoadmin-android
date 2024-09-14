package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.datasource.StatesRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entities.States
import javax.inject.Inject

class StatesDataSourceRemote @Inject constructor(
    private val api: AdminApiOrdersNetwork
) : StatesRemote {
    override suspend fun getStates(): ResultState<List<States>> {
        val response = try {
            api.getStates().body()?.payload!!.map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
