package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.datasource.OrderStatusRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entities.Order
import javax.inject.Inject

class OrderStatusDataSourceRemote @Inject constructor(
    private val api: AdminApiOrdersNetwork
) : OrderStatusRemote {
    override suspend fun getStatus(): ResultState<List<Order>> {
        val response = try {
            api.getOrderStatus("648f94bd704db9741d1d2c04").body()?.payload!!.map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
