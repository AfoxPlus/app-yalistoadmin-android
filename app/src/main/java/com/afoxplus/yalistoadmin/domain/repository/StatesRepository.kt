package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity

interface StatesRepository {
    suspend fun getStates(): ResultState<List<StatesEntity>>
    suspend fun saveStates(statesEntity: List<StatesEntity>): ResultState<Unit>
}
