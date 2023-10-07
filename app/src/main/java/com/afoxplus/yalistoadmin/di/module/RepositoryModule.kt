package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.yalistoadmin.data.repository.AuthRepositoryData
import com.afoxplus.yalistoadmin.data.repository.OrderStatusRepositoryData
import com.afoxplus.yalistoadmin.data.repository.ProductRepositoryData
import com.afoxplus.yalistoadmin.data.repository.StatesRepositoryData
import com.afoxplus.yalistoadmin.domain.repository.AuthRepository
import com.afoxplus.yalistoadmin.domain.repository.OrderStatusRepository
import com.afoxplus.yalistoadmin.domain.repository.ProductRepository
import com.afoxplus.yalistoadmin.domain.repository.StatesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsAuthRepository(
        repositoryData: AuthRepositoryData
    ): AuthRepository

    @Binds
    abstract fun bindsStatesRepository(
        repositoryData: StatesRepositoryData
    ): StatesRepository

    @Binds
    abstract fun bindsOrderStatusRepository(
        repositoryData: OrderStatusRepositoryData
    ): OrderStatusRepository

    @Binds
    abstract fun bindProductRepository(
        productRepositoryData: ProductRepositoryData
    ): ProductRepository
}
