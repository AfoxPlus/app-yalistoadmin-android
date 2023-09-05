package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order

interface OrderStatusRepository {
    suspend fun getStatus(): ResultState<List<Order>>
}
