package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity

interface StatesDataSourceRemote {
    suspend fun getStates(): ResultState<List<StatesEntity>>
}
