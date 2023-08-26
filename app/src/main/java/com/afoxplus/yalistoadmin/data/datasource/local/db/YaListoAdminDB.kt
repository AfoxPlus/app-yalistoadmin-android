package com.afoxplus.yalistoadmin.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.afoxplus.yalistoadmin.data.datasource.local.db.dao.StatesDAO
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.StatesDB

@Database(entities = [StatesDB::class], version = 1, exportSchema = true)
abstract class YaListoAdminDB : RoomDatabase() {
    abstract fun statesDAO(): StatesDAO
}
