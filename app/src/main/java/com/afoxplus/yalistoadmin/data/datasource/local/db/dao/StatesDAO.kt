package com.afoxplus.yalistoadmin.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.afoxplus.yalistoadmin.data.datasource.local.db.model.StatesDB

@Dao
interface StatesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveStates(states: List<StatesDB>)

    @Query("SELECT * FROM states")
    fun getStates(): List<StatesDB>
}
