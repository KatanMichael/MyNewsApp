package com.michael.mynewsapp.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.michael.mynewsapp.controllers.Repository
import com.michael.mynewsapp.models.Article

class MainViewModel : ViewModel()
{
    val repository = Repository



    var headLinesList :  MutableLiveData<ArrayList<Article>>
    init {
        headLinesList = repository.initHeadLinesList()
    }
}
