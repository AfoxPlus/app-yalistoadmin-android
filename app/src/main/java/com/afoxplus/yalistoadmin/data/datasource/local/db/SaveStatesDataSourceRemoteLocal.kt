package com.afoxplus.yalistoadmin.data.datasource.local.db

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSourceLocal
import com.afoxplus.yalistoadmin.data.datasource.local.db.dao.StatesDAO
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.toDB
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.toEntity
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import javax.inject.Inject

class SaveStatesDataSourceRemoteLocal @Inject constructor(
    private val statesDAO: StatesDAO
) : StatesDataSourceLocal {
    override suspend fun saveStates(statesEntity: List<StatesEntity>): ResultState<Unit> {
        val response = try {
            statesDAO.saveStates(statesEntity.map { it.toDB() })
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }

    override suspend fun getStates(): ResultState<List<StatesEntity>> {
        val response = try {
            statesDAO.getStates().map { it.toEntity() }
        } catch (e: Exception) {
            return ResultState.Error(e)
        }
        return ResultState.Success(response)
    }
}
