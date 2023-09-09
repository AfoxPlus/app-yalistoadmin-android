package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States

interface StatesDbLocal {
    suspend fun saveStates(states: List<States>): ResultState<Unit>
    suspend fun getStates(): ResultState<List<States>>
}
