package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order

interface OrderStatusRemote {
    suspend fun getStatus(): ResultState<List<Order>>
}
