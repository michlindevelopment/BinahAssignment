package com.binah.api
import com.binah.data.DefaultData
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //Retrofit API client for data retrieve
    val getClient: ApiService
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl("${DefaultData.BASE_URL}${DefaultData.VERSION}/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(ApiService::class.java)

        }
}