package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSource
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class StatesRepositoryData @Inject constructor(
    private val dataSource: StatesDataSource
) : StatesRepository {
    override suspend fun states(): ResultState<List<StatesEntity>> {
        return dataSource.states()
    }
}
