package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Order

fun interface OrderRemote {
    suspend fun archiveOrder(order: Order): ResultState<Unit>
}
