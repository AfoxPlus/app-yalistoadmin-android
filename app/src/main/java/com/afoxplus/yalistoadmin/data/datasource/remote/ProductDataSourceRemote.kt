package com.afoxplus.yalistoadmin.data.datasource.remote

import com.afoxplus.yalistoadmin.commons.utils.ResultState
import com.afoxplus.yalistoadmin.data.api.AdminApiProductNetwork
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.toEntity
import com.afoxplus.yalistoadmin.domain.entities.Product
import javax.inject.Inject

class ProductDataSourceRemote @Inject constructor(private val api: AdminApiProductNetwork) {

    suspend fun getProductsByRestaurant(restaurantCode: String): ResultState<List<Product>> {
        val response = try {
            api.searchProducts(restaurantCode = restaurantCode)
                .body()?.payload!!.map { it.toEntity() }
        } catch (ex: Exception) {
            return ResultState.Error(ex)
        }
        return ResultState.Success(response)
    }
}
