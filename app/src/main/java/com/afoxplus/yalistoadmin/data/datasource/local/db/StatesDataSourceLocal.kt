package com.afoxplus.yalistoadmin.data.datasource.local.db

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.StatesLocal
import com.afoxplus.yalistoadmin.data.datasource.local.db.dao.StatesDAO
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.toDB
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.toEntity
import com.afoxplus.yalistoadmin.domain.entities.States
import javax.inject.Inject

class StatesDataSourceLocal @Inject constructor(
    private val statesDAO: StatesDAO
) : StatesLocal {
    override suspend fun saveStates(states: List<States>): ResultState<Unit> {
        val response = try {
            statesDAO.saveStates(states.map { it.toDB() })
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }

    override suspend fun getStates(): ResultState<List<States>> {
        val response = try {
            statesDAO.getStates().map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
