package com.afoxplus.yalistoadmin.data.datasource.local.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.afoxplus.yalistoadmin.commons.constants.ConstantsDB.DB_TABLE_STATES
import com.afoxplus.yalistoadmin.domain.entities.States

@Entity(tableName = DB_TABLE_STATES)
data class StatesDB(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val code: String,
    val name: String
)

fun StatesDB.toEntity(): States {
    return States(
        id = id,
        code = code,
        name = name
    )
}

fun States.toDB(): StatesDB {
    return StatesDB(
        id = id,
        code = code,
        name = name
    )
}
