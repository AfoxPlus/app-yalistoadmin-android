package com.afoxplus.yalistoadmin.commons.utils

sealed class ResultState<T>() {
    //class Init<T> : ResultState<T>()
    //class Loading<T> : ResultState<T>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error<T>(val error: Exception) : ResultState<T>()
}