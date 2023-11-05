package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class GetStatesUseCase @Inject constructor(
    private val repository: StatesRepository
) {
    suspend fun getStates(): ResultState<List<States>> {
        return repository.getStates()
    }

    suspend fun getStateByCode(code: String): ResultState<States> {
        return repository.getStateByCode(code)
    }
}
