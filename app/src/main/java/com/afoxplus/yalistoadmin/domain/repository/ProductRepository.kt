package com.afoxplus.yalistoadmin.domain.repository

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.domain.entities.Product

interface ProductRepository {

    suspend fun getProductsByRestaurant(): ResultState<List<Product>>
    suspend fun updateProduct(product: Product): ResultState<Boolean>
}
