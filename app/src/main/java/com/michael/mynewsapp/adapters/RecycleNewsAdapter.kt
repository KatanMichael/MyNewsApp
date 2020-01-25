package com.michael.mynewsapp.adapters

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.michael.mynewsapp.R
import com.michael.mynewsapp.adapters.RecycleNewsAdapter.NewsViewHolder
import com.michael.mynewsapp.controllers.Repository
import com.michael.mynewsapp.interfaces.RequestListener
import com.michael.mynewsapp.models.Article
import kotlinx.android.synthetic.main.regular_news_cell.view.*
import java.util.*
import kotlin.collections.ArrayList

class RecycleNewsAdapter(val context: Context, var listOfArticles: ArrayList<Article>): RecyclerView.Adapter<NewsViewHolder>()
{
    val repository = Repository

    val DEFULT_ARTICLE_CELL = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder
    {
        val cellView = LayoutInflater.from(context).inflate(R.layout.regular_news_cell
            ,parent
            ,false)

        return NewsViewHolder(cellView)
    }

    override fun getItemCount(): Int {
        return listOfArticles.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int)
    {
        val newsViewHolder = holder as NewsViewHolder
        val currentArticle = listOfArticles[position]
        newsViewHolder.newsCellArticleContent.text = currentArticle.description
        newsViewHolder.newsCellPublishDate.text = currentArticle.publishedAt



        if(newsViewHolder.newsCellArticleImage != null)
        {
            Glide.with(context)
                .load(currentArticle.urlToImage)
                .into(newsViewHolder.newsCellArticleImage)
        }



        repository.getLogoFromUrl(shortenUrl(currentArticle.url),object : RequestListener<Bitmap>
        {
            override fun onComplete(logo: Bitmap)
            {
                if(logo != null)
                {
                    Glide.with(context)
                        .load(logo)
                        .into(newsViewHolder.newsCellSourceLogo)
                }
            }

            override fun onError(msg: String)
            {

            }

        })


    }

    fun updateListOfArticles(updatedListOfArticles: ArrayList<Article>)
    {
        listOfArticles  = updatedListOfArticles
        notifyDataSetChanged()
    }

    fun shortenUrl(url: String): String
    {
        var start = 0
        var end = 0

        while (url[start] != '/')
        {
            start++
        }

        end = start+2

        while(url[end] != '/')
        {
            end++
        }

        return url.substring(start+2,end)
    }

    class NewsViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        var newsCellArticleImage = view.newsCellArticleImage
        var newsCellSourceLogo = view.newsCellSourceLogo
        var newsCellArticleContent = view.newsCellArticleContent
        var newsCellPublishDate = view.newsCellPublishDate
        var newsCellOptions = view.newsCellOptions

    }



}