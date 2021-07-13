package com.example.kotlin

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
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

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currentMeme: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        loadMeme()

        binding.next.setOnClickListener { loadMeme() }

        binding.share.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = ("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "Hey checkout this cool meme : $currentMeme")
            val chooser = Intent.createChooser(intent, "Share Meme Using..")
            startActivity(chooser)
        }

    }

    private fun loadMeme() {
        val url = "https://meme-api.herokuapp.com/gimme"
        binding.progressBar.visibility = View.VISIBLE
        val jsonRequest = JsonObjectRequest(Request.Method.GET, url, null, {
            currentMeme = it.getString("url")
            Glide.with(this).load(currentMeme).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.progressBar.visibility = View.GONE
                    return false
                }
            }).into(binding.image)
        }, { })

        MySingleton.getInstance(this).addToRequestQueue(jsonRequest)
    }

}