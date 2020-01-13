package com.michael.mynewsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.michael.mynewsapp.R
import com.michael.mynewsapp.adapters.RecycleNewsAdapter.NewsViewHolder
import com.michael.mynewsapp.models.Article
import kotlinx.android.synthetic.main.regular_news_cell.view.*

class RecycleNewsAdapter(val context: Context, var listOfArticles: ArrayList<Article>): RecyclerView.Adapter<NewsViewHolder>()
{


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
        newsViewHolder.newsCellArticleContent.text = currentArticle.content
        newsViewHolder.newsCellPublishDate.text = currentArticle.publishedAt

        if(newsViewHolder.newsCellArticleImage != null)
        {
            Glide.with(context)
                .load(currentArticle.urlToImage)
                .into(newsViewHolder.newsCellArticleImage)
        }else
        {

        }


    }

    fun updateListOfArticles(updatedListOfArticles: ArrayList<Article>)
    {
        listOfArticles  = updatedListOfArticles
        notifyDataSetChanged()
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