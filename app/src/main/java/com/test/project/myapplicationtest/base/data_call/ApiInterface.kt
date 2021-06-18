package com.test.project.myapplicationtest.base.data_call

import com.test.project.myapplicationtest.base.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */
interface ApiInterface {
    @GET("api")
    suspend fun getUserData(@Query("results") result: Int): Response<UserResponse>
}