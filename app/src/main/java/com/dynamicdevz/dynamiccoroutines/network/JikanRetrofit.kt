package com.dynamicdevz.dynamiccoroutines.network

import com.dynamicdevz.dynamiccoroutines.model.JikanResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class JikanRetrofit {

    //https://api.jikan.moe?q=naruto

    val jikanService = Retrofit.Builder()
        .baseUrl("https://api.jikan.moe")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(JikanService::class.java)

    interface JikanService {

        @GET("/v3/search/anime")
        fun getMediaItemsAsync(@Query("q") query: String): Deferred<JikanResponse>

    }
}







