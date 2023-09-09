package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.OrderStatusRemote
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.RestaurantParams
import javax.inject.Inject

class OrderStatusRepositoryData @Inject constructor(
    private val dataSource: OrderStatusRemote
) : OrderStatusRepository {
    override suspend fun getStatus(params: RestaurantParams): ResultState<List<Order>> {
        return dataSource.getStatus(params)
    }
}
