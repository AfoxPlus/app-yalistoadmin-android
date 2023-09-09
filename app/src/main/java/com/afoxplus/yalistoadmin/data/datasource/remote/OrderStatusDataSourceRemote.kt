package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.datasource.OrderStatusRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.model.request.toRequest
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams
import javax.inject.Inject

class OrderStatusDataSourceRemote @Inject constructor(
    private val api: AdminApiOrdersNetwork
) : OrderStatusRemote {
    override suspend fun getStatus(params: RestaurantParams): ResultState<List<Order>> {
        val response = try {
            api.getOrderStatus(code = params.toRequest().code)
                .body()?.payload!!.map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
