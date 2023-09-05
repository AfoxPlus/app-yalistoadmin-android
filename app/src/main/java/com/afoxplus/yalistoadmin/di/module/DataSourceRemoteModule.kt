package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.yalistoadmin.data.datasource.AuthRemote
import com.afoxplus.yalistoadmin.data.datasource.OrderStatusRemote
import com.afoxplus.yalistoadmin.data.datasource.StatesRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.AuthDataSourceRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.OrderStatusDataSourceRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.StatesDataSourceRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceRemoteModule {

    @Binds
    abstract fun bindsAuthRemote(
        dataSourceRemote: AuthDataSourceRemote
    ): AuthRemote

    @Binds
    abstract fun bindsStatesRemote(
        dataSourceRemote: StatesDataSourceRemote
    ): StatesRemote

    @Binds
    abstract fun bindsOrderStatusRemote(
        dataSourceRemote: OrderStatusDataSourceRemote
    ): OrderStatusRemote
}
