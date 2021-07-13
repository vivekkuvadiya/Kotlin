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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = NewsListAdapter(fetchData(), this)
        binding.rv.adapter = adapter

    }

    private fun fetchData(): ArrayList<String> {
        val list = ArrayList<String>()
        for (i in 0 until 100) {
            list.add("item no $i")
        }
        return list
    }

    override fun onNewsClicked(item: String) {
        Toast.makeText(this, item, Toast.LENGTH_SHORT).show()
    }

}