package com.michael.mynewsapp.controllers

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.michael.mynewsapp.interfaces.RequestListener

class VolleyController(context: Context)
{
    val API_KEY = "ab55b01cd3f8430d8c5f618088a55546d2af7294ad65"


    val queue = Volley.newRequestQueue(context)


    fun getLogoWithUrl(requestedUrl: String,requestListener: RequestListener<Bitmap>)
    {

        val url = "https://api.ritekit.com/v1/images/" +
                "logo?domain=$requestedUrl&" +
                "client_id=$API_KEY"

        val imageRequest = ImageRequest(url, Response.Listener<Bitmap>
        {

            if(it != null)
            {
                requestListener.onComplete(it)
            }else
            {
                requestListener.onError("Error!")
            }

        }, 0
            ,0, ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.RGB_565,
            Response.ErrorListener {

                requestListener.onError(it.message.toString())

            })


        queue.add(imageRequest)

    }

}