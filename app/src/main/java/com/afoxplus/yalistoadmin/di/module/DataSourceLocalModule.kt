package com.afoxplus.yalistoadmin.di.module

import com.afoxplus.yalistoadmin.data.datasource.AuthPreferencesLocal
import com.afoxplus.yalistoadmin.data.datasource.StatesDbLocal
import com.afoxplus.yalistoadmin.data.datasource.local.db.StatesDataSourceDbLocal
import com.afoxplus.yalistoadmin.data.datasource.local.preferences.AuthPreferencesDataSourcePreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceLocalModule {

    @Binds
    abstract fun bindsStatesLocal(
        dataSourceLocal: StatesDataSourceDbLocal
    ): StatesDbLocal

    @Binds
    abstract fun bindsAuthPreferencesLocal(
        dataSourceLocal: AuthPreferencesDataSourcePreferences
    ): AuthPreferencesLocal
}
