package com.afoxplus.yalistoadmin.data.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.response.BaseResponse
import com.afoxplus.yalistoadmin.data.datasource.remote.model.request.AuthRequestModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.AuthResponseModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.OrderResponseModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.ProductSearchResponseModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.StatesResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

@ServiceClient(type = UrlProvider.Type.API_RESTAURANTS)
fun interface AdminApiRestaurantNetwork {

    companion object {
        const val PATH_AUTH = "auth"
    }

    @POST("restaurant/$PATH_AUTH")
    suspend fun auth(
        @Body authRequestModel: AuthRequestModel
    ): Response<BaseResponse<AuthResponseModel>>
}

@ServiceClient(type = UrlProvider.Type.API_ORDERS)
interface AdminApiOrdersNetwork {

    companion object {
        const val PATH_STATES = "states"
        const val PATH_STATUS = "restaurant/status"
        const val PATH_SEND_STATE = "send_state"
    }

    @GET("orders/$PATH_STATES")
    suspend fun getStates(): Response<BaseResponse<List<StatesResponseModel>>>

    @GET("orders/$PATH_STATUS")
    suspend fun getOrderStatus(
        @Header("restaurant_code") code: String
    ): Response<BaseResponse<List<OrderResponseModel>>>
}

@ServiceClient(type = UrlProvider.Type.API_PRODUCTS)
fun interface AdminApiProductNetwork {
    companion object {
        const val PATH_SEARCH = "search"
    }

    @GET("product/$PATH_SEARCH")
    suspend fun searchProducts(@Query("restaurant_code") restaurantCode: String): Response<BaseResponse<List<ProductSearchResponseModel>>>
}
