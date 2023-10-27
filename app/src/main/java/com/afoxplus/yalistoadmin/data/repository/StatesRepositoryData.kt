package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.StatesDbLocal
import com.afoxplus.yalistoadmin.data.datasource.StatesRemote
import com.afoxplus.yalistoadmin.domain.entities.States
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class StatesRepositoryData @Inject constructor(
    private val dataSourceRemote: StatesRemote,
    private val dataSourceLocal: StatesDbLocal
) : StatesRepository {
    override suspend fun getStates(): ResultState<List<States>> {
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

    override suspend fun saveStates(states: List<States>): ResultState<Unit> {
        return dataSourceLocal.saveStates(states)
    }

    override suspend fun getStateByCode(code: String): ResultState<States> {
        return dataSourceLocal.getStateByCode(code)
    }
}
