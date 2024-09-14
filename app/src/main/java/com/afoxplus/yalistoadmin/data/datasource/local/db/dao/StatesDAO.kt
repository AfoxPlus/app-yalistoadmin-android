package com.afoxplus.yalistoadmin.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afoxplus.yalistoadmin.cross.constants.ConstantsDB.DB_TABLE_STATES
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.StatesDB

@Dao
interface StatesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStates(states: List<StatesDB>)

    @Query("SELECT * FROM $DB_TABLE_STATES")
    fun getStates(): List<StatesDB>

    @Query("SELECT * FROM $DB_TABLE_STATES WHERE code = :code")
    fun getStateByCode(code: String): StatesDB
}
