package com.afoxplus.yalistoadmin.ui.screens.details.vo

import com.afoxplus.yalistoadmin.domain.entities.States

data class StatesVO(
    val id: String,
    val code: String,
    val name: String,
    var isCheck: Boolean = false
)

fun States.toVO(): StatesVO {
    return StatesVO(
        id = id,
        code = code,
        name = name
    )
}
