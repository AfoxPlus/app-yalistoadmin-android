package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.States
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class SaveStatesUseCase @Inject constructor(
    private val repository: StatesRepository
) {
    suspend fun saveStates(states: List<States>): ResultState<Unit> {
        return repository.saveStates(states)
    }

    suspend fun getStates(): ResultState<List<States>> {
        return repository.getStates()
    }
}
