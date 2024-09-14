package com.afoxplus.yalistoadmin.data.repository

import com.afoxplus.yalistoadmin.cross.utils.ResultState
import com.afoxplus.yalistoadmin.data.datasource.AuthPreferencesLocal
import com.afoxplus.yalistoadmin.data.datasource.remote.ProductDataSourceRemote
import com.afoxplus.yalistoadmin.domain.entities.Product
import com.afoxplus.yalistoadmin.domain.repository.ProductRepository
import javax.inject.Inject

class ProductRepositoryData @Inject constructor(
    private val productDataSourceRemote: ProductDataSourceRemote,
    private val authPreferencesLocal: AuthPreferencesLocal
) : ProductRepository {

    override suspend fun getProductsByRestaurant(): ResultState<List<Product>> {
        val restaurantCode = authPreferencesLocal.getAuth().code
        return productDataSourceRemote.getProductsByRestaurant(restaurantCode = restaurantCode)
    }

    override suspend fun updateProduct(product: Product): ResultState<Boolean> {
        return productDataSourceRemote.updateProduct(product)
    }
}
