package com.task1.news.newVersion.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.task1.news.R

class CategoryAdapter(var category: List<category>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var categoryImage: ImageView = itemView.findViewById(R.id.category_Image)
        var categoryTitle: TextView = itemView.findViewById(R.id.category_Title)
        var background: MaterialCardView = itemView.findViewById(R.id.card_View)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {

        var view = LayoutInflater.from(parent.context).inflate(
            if (viewType == left_Side)
                R.layout.left_side_category
            else
                R.layout.right_side_category, parent, false
        )

        return CategoryViewHolder(view)

    }

    var left_Side = 10
    var right_Side = 20

    override fun getItemViewType(position: Int): Int {

        if (position % 2 == 0)
            return left_Side
        else
            return right_Side
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {


        var items = category[position]

        holder.categoryImage.setImageResource(items.ImageId)
        holder.categoryTitle.setText(items.titleId)
        holder.background.setCardBackgroundColor(
            ContextCompat.getColor(
                holder.itemView.context,
                items.backgroundId
            )
        )

        if (onItemClickListener != null) {

            holder.itemView.setOnClickListener {
                onItemClickListener?.onClick(position, items)
            }
        }

    }


    override fun getItemCount(): Int {

        return category.size
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onClick(position: Int, category: category)
    }
}