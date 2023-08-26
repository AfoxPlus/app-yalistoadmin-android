package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSourceLocal
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSourceRemote
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class StatesRepositoryData @Inject constructor(
    private val dataSourceRemote: StatesDataSourceRemote,
    private val dataSourceLocal: StatesDataSourceLocal
) : StatesRepository {
    override suspend fun getStates(): ResultState<List<StatesEntity>> {
        return when (val response = dataSourceLocal.getStates()) {
            is ResultState.Error -> {
                ResultState.Error(response.exception)
            }

            is ResultState.Success -> {
                return if (response.data.isEmpty()) {
                    dataSourceRemote.getStates()
                } else {
                    ResultState.Success(response.data)
                }
            }
        }
    }

    override suspend fun saveStates(statesEntity: List<StatesEntity>): ResultState<Unit> {
        return dataSourceLocal.saveStates(statesEntity)
    }
}
