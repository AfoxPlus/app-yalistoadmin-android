package com.afoxplus.yalistoadmin.data.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.response.BaseResponse
import com.afoxplus.yalistoadmin.data.model.request.AuthRequestModel
import com.afoxplus.yalistoadmin.data.model.response.AuthResponseModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

@ServiceClient(type = UrlProvider.Type.API_RESTAURANTS)
interface AdminApiNetwork {

    companion object {
        const val PATH_AUTH = "auth"
    }

    @POST("restaurant/$PATH_AUTH")
    suspend fun auth(
        @Body authRequestModel: AuthRequestModel
    ): Response<BaseResponse<AuthResponseModel>>

}