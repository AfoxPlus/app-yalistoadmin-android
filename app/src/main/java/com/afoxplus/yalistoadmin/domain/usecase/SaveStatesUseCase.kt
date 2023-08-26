package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class SaveStatesUseCase @Inject constructor(
    private val repository: StatesRepository
) {
    suspend fun saveStates(statesEntity: List<StatesEntity>): ResultState<Unit> {
        return repository.saveStates(statesEntity)
    }

    suspend fun getStates(): ResultState<List<StatesEntity>> {
        return repository.getStates()
    }
}
