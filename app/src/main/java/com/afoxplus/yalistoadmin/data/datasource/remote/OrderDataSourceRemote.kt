package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiOrdersNetwork
import com.afoxplus.yalistoadmin.data.datasource.OrderRemote
import com.afoxplus.yalistoadmin.domain.entities.Order
import javax.inject.Inject

class OrderDataSourceRemote @Inject constructor(
    private val api: AdminApiOrdersNetwork
) : OrderRemote {
    override suspend fun archiveOrder(order: Order): ResultState<Unit> {
        try {
            api.archiveOrder(orderId = order.id)
                .body()?.success ?: return ResultState.Error(Exception())
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(Unit)
    }
}
