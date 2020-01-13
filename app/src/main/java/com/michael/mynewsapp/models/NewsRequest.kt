package com.michael.mynewsapp.models

data class NewsRequest(
    val articles: ArrayList<Article>,
    val status: String,
    val totalResults: Int
)