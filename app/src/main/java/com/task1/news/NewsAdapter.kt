package com.task1.news

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.task1.news.Model.ArticlesItem

class NewsAdapter(var news: List<ArticlesItem?>?) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsImage: ImageView = itemView.findViewById(R.id.newsImage)
        var newsSourceName: TextView = itemView.findViewById(R.id.newsSourceName)
        var newsDescription: TextView = itemView.findViewById(R.id.newsDescription)
        var newsTime: TextView = itemView.findViewById(R.id.newsTime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)

        return NewsViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        var items = news?.get(position)

        holder.newsDescription.setText(items?.description)
        holder.newsTime.setText(items?.publishedAt)
        holder.newsSourceName.setText(items?.source?.name)

        Glide.with(holder.itemView)
            .load(items?.urlToImage)
            .into(holder.newsImage)

        if (onItemClick != null) {

            holder.itemView.setOnClickListener {

                onItemClick?.onClick(position, items!!)

            }
        }

    }

    override fun getItemCount(): Int {

        return news?.size ?: 0

    }

    fun refreashAdapter(latestNews: List<ArticlesItem?>?) {

        this.news = latestNews

        notifyDataSetChanged()
    }


    var onItemClick: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onClick(position: Int, item: ArticlesItem)
    }
}