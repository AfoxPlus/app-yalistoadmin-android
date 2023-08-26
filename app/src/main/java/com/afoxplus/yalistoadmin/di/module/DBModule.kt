package com.afoxplus.yalistoadmin.di.module

import android.content.Context
import androidx.room.Room
import com.afoxplus.yalistoadmin.commons.constants.ConstantsDB.DB_NAME
import com.afoxplus.yalistoadmin.data.datasource.StatesDataSourceLocal
import com.afoxplus.yalistoadmin.data.datasource.local.db.SaveStatesDataSourceRemoteLocal
import com.afoxplus.yalistoadmin.data.datasource.local.db.YaListoAdminDB
import com.afoxplus.yalistoadmin.data.datasource.local.db.dao.StatesDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DBModule {

    @Provides
    fun providesYaListoAdminDB(@ApplicationContext context: Context): YaListoAdminDB {
        return Room.databaseBuilder(context, YaListoAdminDB::class.java, DB_NAME).build()
    }

    @Provides
    fun providesStatesDAO(yaListoAdminDB: YaListoAdminDB): StatesDAO {
        return yaListoAdminDB.statesDAO()
    }

    @Provides
    fun providesStatesDataSourceLocal(statesDAO: StatesDAO): StatesDataSourceLocal {
        return SaveStatesDataSourceRemoteLocal(statesDAO)
    }
}
