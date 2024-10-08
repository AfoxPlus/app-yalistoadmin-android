package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States

fun interface StatesRemote {
    suspend fun getStates(): ResultState<List<States>>
}
