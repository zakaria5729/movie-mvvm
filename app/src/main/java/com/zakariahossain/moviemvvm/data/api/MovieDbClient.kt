package com.zakariahossain.moviemvvm.data.api

import com.zakariahossain.moviemvvm.util.Constants.API_KEY
import com.zakariahossain.moviemvvm.util.Constants.BASE_URL
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object MovieDbClient {

    private fun getClient(): Retrofit {
        val requestInterceptor = Interceptor { chain ->
            val url: HttpUrl = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getApiService(): MovieDbApiService = getClient().create(MovieDbApiService::class.java)
}