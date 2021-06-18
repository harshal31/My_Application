package com.test.project.myapplicationtest.base.data_call

import com.google.gson.GsonBuilder
import com.test.project.myapplicationtest.ui.util.AppLevelSingleton
import com.test.project.myapplicationtest.ui.util.Constants
import com.test.project.myapplicationtest.ui.util.isConnected
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.Request
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
        val cache = Cache(AppLevelSingleton.application.cacheDir, (5 * 1024 * 1024).toLong())
        okHttpClient.cache(cache)
        okHttpClient.connectTimeout(240, TimeUnit.SECONDS)
        okHttpClient.readTimeout(240, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(240, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(httpLoggingInterceptor)

        okHttpClient.addInterceptor { chain ->
            val builder: Request.Builder = chain.request().newBuilder()
            chain.proceed(builder.build())
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