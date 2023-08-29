package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States

interface StatesRemote {
    suspend fun getStates(): ResultState<List<States>>
}
