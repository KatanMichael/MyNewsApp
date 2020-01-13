package com.michael.mynewsapp.controllers

import androidx.lifecycle.MutableLiveData
import com.michael.mynewsapp.models.Article
import java.util.ArrayList

object Repository
{
    val retroController = RetroController

    fun initHeadLinesList(): MutableLiveData<ArrayList<Article>>
    {
        return retroController.initHeadLines()
    }

}