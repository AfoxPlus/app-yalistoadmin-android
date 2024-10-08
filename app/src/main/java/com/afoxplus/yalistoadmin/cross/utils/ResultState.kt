package com.afoxplus.yalistoadmin.cross.utils

sealed interface ResultState<T> {
    data class Success<T>(val data: T) : ResultState<T>
    data class Error<T>(val exception: Exception) : ResultState<T>
}
