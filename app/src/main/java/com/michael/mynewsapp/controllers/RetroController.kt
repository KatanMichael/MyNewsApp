package com.michael.mynewsapp.controllers

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.michael.mynewsapp.interfaces.newsRetroService
import com.michael.mynewsapp.models.Article
import com.michael.mynewsapp.models.NewsRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroController
{
    val API_KEY = "46ba9445151f45feb3cf21b3bae4a63e"

    val retroClient = Retrofit.Builder()
        .baseUrl("https://newsapi.org/"
        ).addConverterFactory(GsonConverterFactory.create())
        .build()

    val retroService = retroClient.create(newsRetroService::class.java)


    fun initHeadLines() : MutableLiveData<ArrayList<Article>>
    {
        val resultLiveData = MutableLiveData<ArrayList<Article>>()

        retroService.getHeadlinesByCountry(API_KEY)
            .enqueue(object : Callback<NewsRequest>
            {
                override fun onFailure(call: Call<NewsRequest>, t: Throwable)
                {
                    Log.d("RetroController", t.message)
                    resultLiveData.postValue(null)
                }

                override fun onResponse(call: Call<NewsRequest>, response: Response<NewsRequest>)
                {
                    if(response.body() != null)
                    {
                        resultLiveData.postValue(response.body()!!.articles)
                    }
                }

            })


        return resultLiveData

    }
}