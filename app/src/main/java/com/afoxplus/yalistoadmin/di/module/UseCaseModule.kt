package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.usecase.GetAuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object UseCaseModule {

  /*  @Provides
    fun provideGetAuthUseCase(repository: AuthRepository): GetAuthUseCase {
        return GetAuthUseCase(repository::auth)
    }*/

}