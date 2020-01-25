package com.michael.mynewsapp.interfaces

import com.michael.mynewsapp.models.NewsRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface newsRetroService
{

    @GET("/v2/top-headlines")
    fun getHeadlinesByCountry(@Query("apiKey") apiKey : String,
                              @Query("country")country:String = "il",
                              @Query("category")category: String = "general",
                              @Query("pageSize")pageSize: Int = 100): Call<NewsRequest>

}