package com.michael.mynewsapp.controllers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.michael.mynewsapp.interfaces.RequestListener
import com.michael.mynewsapp.models.Article
import java.io.ByteArrayOutputStream
import java.lang.Exception
import java.util.ArrayList

object Repository
{
    val retroController = RetroController
    val logosMap = HashMap<String, Bitmap>()
    val firebaseStorage = FirebaseStorage.getInstance()
    val reference = firebaseStorage.reference
    lateinit var volleyController: VolleyController

    fun updateContext(context: Context)
    {
        volleyController = VolleyController(context)
    }

    fun initHeadLinesList(): MutableLiveData<ArrayList<Article>>
    {
        return retroController.initHeadLines()
    }

    fun getLogoFromUrl(url: String,requestListener: RequestListener<Bitmap>)
    {
        if(logosMap.containsKey(url))
        {
            requestListener.onComplete(logosMap[url]!!)
        }else
        {

            val ONE_MEGABYTE: Long = 1024 * 1024
            reference.child("/logos/$url.jpg").getBytes(ONE_MEGABYTE)
                .addOnCompleteListener(object: OnCompleteListener<ByteArray>
                {
                    override fun onComplete(task: Task<ByteArray>)
                    {
                        if(task.isSuccessful)
                        {
                            val decodeByteArray = BitmapFactory.decodeByteArray(
                                task.result,
                                0,
                                task.result!!.size)

                            logosMap[url] = decodeByteArray

                            requestListener.onComplete(decodeByteArray)

                        }else
                        {
                            volleyController.getLogoWithUrl(url, object : RequestListener<Bitmap>
                            {
                                override fun onComplete(t: Bitmap)
                                {
                                    if(t != null)
                                    {
                                        val baos = ByteArrayOutputStream()
                                        t.compress(Bitmap.CompressFormat.JPEG,100,baos)
                                        val logoByteArray = baos.toByteArray()

                                        reference.child("/logos/$url.jpg").putBytes(logoByteArray)
                                            .addOnSuccessListener(object : OnSuccessListener<UploadTask.TaskSnapshot>
                                            {
                                                override fun onSuccess(task: UploadTask.TaskSnapshot?)
                                                {

                                                }

                                            })
                                            .addOnFailureListener(object: OnFailureListener
                                            {
                                                override fun onFailure(p0: Exception) {


                                                }

                                            })

                                        logosMap[url] = t
                                        requestListener.onComplete(t)
                                    }
                                }

                                override fun onError(msg: String)
                                {
                                    requestListener.onError(msg)
                                }

                            })
                        }

                    }

                })

        }
    }

}