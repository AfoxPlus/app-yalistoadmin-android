package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.yalistoadmin.data.datasource.StatesLocal
import com.afoxplus.yalistoadmin.data.datasource.local.db.StatesDataSourceLocal
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceLocalModule {

    @Binds
    abstract fun bindsStatesLocal(
        dataSourceLocal: StatesDataSourceLocal
    ): StatesLocal
}
