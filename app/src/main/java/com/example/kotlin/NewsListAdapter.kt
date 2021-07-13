package com.example.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.databinding.ItemNewsBinding

class NewsListAdapter(private val listener: NewsClicked) :
    RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        val news = items[position]
        Glide.with(holder.itemView.context).load(news.image).into(holder.binding.imageView)
        holder.binding.tvTitle.text = news.title
        holder.binding.tvOther.visibility = View.VISIBLE
        holder.binding.tvOther.text = news.author


        holder.itemView.setOnClickListener {
            listener.onNewsClicked(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateNews(newsList: ArrayList<News>) {
        this.items.addAll(newsList)
        notifyDataSetChanged()
    }
}

class NewsViewHolder(itemView: ItemNewsBinding) : RecyclerView.ViewHolder(itemView.root) {
    var binding: ItemNewsBinding = itemView
}

interface NewsClicked {
    fun onNewsClicked(item: News)
}