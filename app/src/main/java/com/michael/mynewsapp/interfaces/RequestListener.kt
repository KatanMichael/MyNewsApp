package com.michael.mynewsapp.interfaces

interface RequestListener<T>
{
    fun onComplete(t: T)
    fun onError(msg: String)

}