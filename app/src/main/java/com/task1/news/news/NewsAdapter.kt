package com.task1.news.news

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.task1.domain.model.ArticlesItemDTO
import com.task1.news.R
import com.task1.news.databinding.NewsItemBinding

class NewsAdapter(var news: List<ArticlesItemDTO?>?) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val itemBinding: NewsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.news_item, parent, false
        )

        return NewsViewHolder(itemBinding)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {

        var items = news?.get(position)
        holder.bindData(items)

        if (onItemClick != null) {

            holder.itemView.setOnClickListener {

                onItemClick?.onClick(position, items!!)

            }
        }
    }

    override fun getItemCount(): Int {

        return news?.size ?: 0

    }

    fun refreashAdapter(latestNews: List<ArticlesItemDTO?>?) {

        this.news = latestNews
        notifyDataSetChanged()
    }


    var onItemClick: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onClick(position: Int, item: ArticlesItemDTO)
    }

    class NewsViewHolder(private val newsItemBinding: NewsItemBinding) :
        RecyclerView.ViewHolder(newsItemBinding.root) {

        fun bindData(item: ArticlesItemDTO?) {
            newsItemBinding.item = item
            newsItemBinding.executePendingBindings()
        }
    }
}