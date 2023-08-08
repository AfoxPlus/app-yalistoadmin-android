package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entity.AuthEntity
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.usecase.params.AuthParams
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun auth(params: AuthParams): ResultState<AuthEntity> {
        return repository.auth(params)
    }
}
