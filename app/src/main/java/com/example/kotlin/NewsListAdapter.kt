package com.example.kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.databinding.ItemNewsBinding

class NewsListAdapter(private val items: ArrayList<String>, private val listener: NewsClicked) :
    RecyclerView.Adapter<NewsViewHolder>() {

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
        holder.binding.text.text = items[position]
        holder.itemView.setOnClickListener {
            listener.onNewsClicked(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class NewsViewHolder(itemView: ItemNewsBinding) : RecyclerView.ViewHolder(itemView.root) {
    var binding: ItemNewsBinding = itemView
}

interface NewsClicked {
    fun onNewsClicked(item: String)
}