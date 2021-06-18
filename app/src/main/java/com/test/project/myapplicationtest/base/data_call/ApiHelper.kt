package com.test.project.myapplicationtest.base.data_call

import com.test.project.myapplicationtest.base.UserResponse
import retrofit2.Response

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */
interface ApiHelper {
    suspend fun getUserData(result: Int): Response<UserResponse>

}