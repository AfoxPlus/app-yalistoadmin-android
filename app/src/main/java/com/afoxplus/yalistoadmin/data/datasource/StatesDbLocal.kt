package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States

interface StatesDbLocal {
    suspend fun saveStates(states: List<States>): ResultState<Unit>
    suspend fun getStates(): ResultState<List<States>>
    suspend fun getStateByCode(code: String): ResultState<States>
}
