package com.afoxplus.yalistoadmin.domain.usecase

import com.afoxplus.yalistoadmin.domain.entities.Auth
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import javax.inject.Inject

class SaveAuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend fun saveAuth(auth: Auth) {
        return repository.saveAuth(auth = auth)
    }
}
