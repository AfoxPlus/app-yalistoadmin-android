package com.afoxplus.yalistoadmin.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Client(
    val name: String,
    val cel: String,
    val addressReference: String
) : Parcelable
