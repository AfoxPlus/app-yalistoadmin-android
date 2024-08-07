package com.afoxplus.yalistoadmin.data.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.response.BaseResponse
import com.afoxplus.yalistoadmin.data.datasource.remote.model.request.AuthRequestModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.request.OrderStateRequestModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.request.ProductUpdateRequestModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.AuthResponseModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.OrderResponseModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.ProductSearchResponseModel
import com.afoxplus.yalistoadmin.data.datasource.remote.model.response.StatesResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

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
        const val PATH_ARCHIVE = "archive"
    }

    @GET("orders/$PATH_STATES")
    suspend fun getStates(): Response<BaseResponse<List<StatesResponseModel>>>

    @GET("orders/$PATH_STATUS/{stateId}")
    suspend fun getOrderStatus(
        @Header("restaurant_code") code: String,
        @Path("stateId") stateId: String
    ): Response<BaseResponse<List<OrderResponseModel>>>

    @PUT("orders/$PATH_SEND_STATE")
    suspend fun sendOrderState(
        @Body orderStateRequest: OrderStateRequestModel
    ): Response<BaseResponse<OrderResponseModel>>

    @DELETE("orders/$PATH_ARCHIVE/{orderId}")
    suspend fun archiveOrder(@Path("orderId") orderId: String): Response<BaseResponse<Unit>>
}

@ServiceClient(type = UrlProvider.Type.API_PRODUCTS)
interface AdminApiProductNetwork {
    companion object {
        const val PATH_SEARCH = "search"
        const val PATH_UPDATE = "show_in_app"
    }

    @GET("product/$PATH_SEARCH")
    suspend fun searchProducts(@Header("restaurant_code") restaurantCode: String): Response<BaseResponse<List<ProductSearchResponseModel>>>

    @PUT("product/$PATH_UPDATE")
    suspend fun updateProduct(@Body productResponse: ProductUpdateRequestModel): Response<BaseResponse<Any>>
}
