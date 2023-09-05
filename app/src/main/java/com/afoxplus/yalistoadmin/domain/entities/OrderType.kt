package com.afoxplus.yalistoadmin.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrderType(
    val code: String,
    val title: String,
    val description: String
) : Parcelable
