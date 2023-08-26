package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.yalistoadmin.data.datasource.AuthDataSource
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSourceRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.AuthDataSourceRemote
import com.afoxplus.yalistoadmin.data.datasource.remote.StatesDataSourceRemoteRemote
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindsAuthDataSource(
        dataSourceRemote: AuthDataSourceRemote
    ): AuthDataSource

    @Binds
    abstract fun bindsStatesDataSource(
        dataSourceRemote: StatesDataSourceRemoteRemote
    ): StatesDataSourceRemote
}
