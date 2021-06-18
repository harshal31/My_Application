package com.test.project.myapplicationtest.base.data_call

import android.app.Application
import com.google.gson.GsonBuilder
import com.test.project.myapplicationtest.ui.util.Constants
import com.test.project.myapplicationtest.ui.util.isConnected
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Harshal Chaudhari on 16/06/21.
 */
object ApiManager : ApiHelper {

    private val okHttpClient = OkHttpClient.Builder()
    private val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val gson = GsonBuilder().setPrettyPrinting().setLenient().serializeNulls().create()!!

    init {
        okHttpClient.connectTimeout(240, TimeUnit.SECONDS)
        okHttpClient.readTimeout(240, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(240, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(httpLoggingInterceptor)
    }

    fun handleNetworkCallWithCache(application: Application) {
        val cache = Cache(application.cacheDir, (5 * 1024 * 1024).toLong())
        okHttpClient.cache(cache)

        okHttpClient.addInterceptor {
            var request = it.request()
            request = if (application.isConnected()) {
                request.newBuilder().header(Constants.CACHE_CONTROL, Constants.PUBLIC_MAX_AGE + 5).build()
            } else {
                request.newBuilder().header(Constants.CACHE_CONTROL, Constants.PUBLIC_ONLY_CACHED + (60 * 60 * 24 * 7)).build()
            }
            it.proceed(request)
        }
    }

    private val apiInterface = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient.build())
        .build()
        .create(ApiInterface::class.java)


    override suspend fun getUserData(result: Int) = apiInterface.getUserData(result)
}