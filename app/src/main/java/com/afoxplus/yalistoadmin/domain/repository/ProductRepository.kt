package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Product

fun interface ProductRepository {

    suspend fun getProductsByRestaurant(): ResultState<List<Product>>
}
