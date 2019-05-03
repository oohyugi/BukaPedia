package com.oohyugi.bukasempak.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by oohyugi on 2019-05-01.
 * github: https://github.com/oohyugi
 */
object ApiClient {

     fun makeService(baseURL: String): ApiServices {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
//            .readTimeout(600, TimeUnit.SECONDS)
//            .connectTimeout(600, TimeUnit.SECONDS)
//            .writeTimeout(600, TimeUnit.SECONDS)
//            .addInterceptor(interceptor)
            .build()
        val retrofit =  Retrofit.Builder().baseUrl(baseURL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiServices::class.java)
    }



}
