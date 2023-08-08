package com.afoxplus.yalistoadmin.data.datasource

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity

interface StatesDataSource {
    suspend fun states(): ResultState<List<StatesEntity>>
}