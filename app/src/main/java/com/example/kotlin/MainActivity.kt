package com.example.kotlin

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kotlin.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), NewsClicked {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rv.layoutManager = LinearLayoutManager(this)
        adapter = NewsListAdapter(this)
        binding.rv.adapter = adapter
        fetchData()
        loadMeme()

    }


    private fun fetchData() {
//    1f4a12d2698e432ea9cf18126dcc7acd
        val url =
            "https://newsapi.org/v2/top-headlines?country=us&apiKey=af2a204612cd437e975c3815ad94de0b"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            val jsonArray = it.getJSONArray("articles")
            val newsList = ArrayList<News>()
            for (i in 0 until jsonArray.length()) {
                val newsObject = jsonArray.getJSONObject(i)
                val news = News(
                    newsObject.getString("title"),
                    newsObject.getString("author"),
                    newsObject.getString("url"),
                    newsObject.getString("urlToImage")
                )
                newsList.add(news)
            }
            adapter.updateNews(newsList)
        }, { Toast.makeText(this, "onError ${it.message}", Toast.LENGTH_SHORT).show() })

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onNewsClicked(item: News) {
        Toast.makeText(this, item.title, Toast.LENGTH_SHORT).show()
    }

    private fun loadMeme() {
        val url = "https://meme-api.herokuapp.com/gimme"
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            Toast.makeText(this, it.getString("url"), Toast.LENGTH_SHORT).show()
        }, { Toast.makeText(this, "OnError ${it.message}", Toast.LENGTH_SHORT).show() })

        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }
}