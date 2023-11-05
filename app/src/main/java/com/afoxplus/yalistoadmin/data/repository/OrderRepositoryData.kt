package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.OrderRemote
import com.afoxplus.yalistoadmin.domain.entities.Order
import com.afoxplus.yalistoadmin.domain.repository.OrderRepository
import javax.inject.Inject

class OrderRepositoryData @Inject constructor(private val orderRemote: OrderRemote) :
    OrderRepository {
    override suspend fun archiveOrder(order: Order): ResultState<Unit> {
        return orderRemote.archiveOrder(order)
    }
}
