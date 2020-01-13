package com.michael.mynewsapp.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.michael.mynewsapp.R
import com.michael.mynewsapp.adapters.RecycleNewsAdapter
import com.michael.mynewsapp.models.Article
import com.michael.mynewsapp.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        val recycleNewsAdapter = RecycleNewsAdapter(view.context, ArrayList<Article>())

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        mainRecycleView.adapter = recycleNewsAdapter
        mainRecycleView.layoutManager = LinearLayoutManager(view.context)

        recycleNewsAdapter.notifyDataSetChanged()

        viewModel.headLinesList.observe(this, Observer
        {
            Toast.makeText(context,"Updated!",Toast.LENGTH_SHORT).show()

            recycleNewsAdapter.updateListOfArticles(it)
        })

    }
}
