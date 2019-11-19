package com.influx2.network

import android.content.Context
import com.influx2.Utils.Constants
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class ApiService(context: Context) {

    init {
        ApiService.context = context
    }

    companion object {
        lateinit var context: Context

        val apiService: ApiList

        init {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(Constants.BASEURL)
                    .build()
            apiService = retrofit.create(ApiList::class.java)
        }
    }
}
