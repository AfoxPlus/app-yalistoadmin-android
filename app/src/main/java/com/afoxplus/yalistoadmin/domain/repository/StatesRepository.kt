package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States

interface StatesRepository {
    suspend fun getStates(): ResultState<List<States>>
    suspend fun saveStates(states: List<States>): ResultState<Unit>
    suspend fun getStateByCode(code: String): ResultState<States>
}
