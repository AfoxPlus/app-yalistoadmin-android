package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.StatesEntity
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import javax.inject.Inject

class GetStatesUseCase @Inject constructor(
    private val repository: StatesRepository
) {
    suspend fun states(): ResultState<List<StatesEntity>> {
        return repository.states()
    }
}
