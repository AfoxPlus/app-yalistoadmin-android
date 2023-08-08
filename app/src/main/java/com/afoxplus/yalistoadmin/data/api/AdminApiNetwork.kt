package com.afoxplus.yalistoadmin.data.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.response.BaseResponse
import com.afoxplus.yalistoadmin.data.model.request.AuthRequestModel
import com.afoxplus.yalistoadmin.data.model.response.AuthResponseModel
import com.afoxplus.yalistoadmin.data.model.response.StatesResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

@ServiceClient(type = UrlProvider.Type.API_RESTAURANTS)
interface AdminApiRestaurantNetwork {

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
        const val PATH_STATUS = "status"
        const val PATH_SEND_STATE = "send_state"
    }

    @GET("orders/${PATH_STATES}")
    suspend fun states(): Response<BaseResponse<List<StatesResponseModel>>>

}