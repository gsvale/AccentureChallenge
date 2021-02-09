package com.example.accenturechallenge.network

import com.example.accenturechallenge.utils.Constants.API_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkClient {


    // Retrofit instance creation

    companion object {
        fun create(): NetworkInterface {
            val retrofit: Retrofit = Retrofit.Builder().baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            return retrofit.create(NetworkInterface::class.java)
        }

    }

}