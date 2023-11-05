package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order

interface OrderRepository {
    suspend fun archiveOrder(order: Order): ResultState<Unit>
}
