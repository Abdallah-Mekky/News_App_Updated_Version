package com.task1.news.category

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.task1.news.R
import com.task1.news.databinding.LeftSideCategoryBinding
import com.task1.news.databinding.RightSideCategoryBinding

class CategoryAdapter(var Category: List<Category?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class RightViewHolder(var rightSideCategoryBinding: RightSideCategoryBinding) :
        RecyclerView.ViewHolder(rightSideCategoryBinding.root) {

        fun bindRightData(item: Category, holder: RecyclerView.ViewHolder) {

            rightSideCategoryBinding.categoryImage.setImageResource(item.ImageId)
            rightSideCategoryBinding.categoryTitle.text = item.titleId
            rightSideCategoryBinding.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    item.backgroundId
                )
            )
        }
    }


    inner class LeftViewHolder(var leftSideCategoryBinding: LeftSideCategoryBinding) :
        RecyclerView.ViewHolder(leftSideCategoryBinding.root) {

        fun bindLeftData(item: Category, holder: RecyclerView.ViewHolder) {

            leftSideCategoryBinding.categoryImage.setImageResource(item.ImageId)
            leftSideCategoryBinding.categoryTitle.text = item.titleId
            leftSideCategoryBinding.cardView.setCardBackgroundColor(
                ContextCompat.getColor(
                    holder.itemView.context,
                    item.backgroundId
                )
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {

            left_Side -> LeftViewHolder(createLeftSide(parent))
            right_Side -> RightViewHolder(createRightSide(parent))
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    var left_Side = 10
    var right_Side = 20

    override fun getItemViewType(position: Int): Int {

        if (position % 2 == 0)
            return left_Side
        else
            return right_Side
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        var items = Category?.get(position)

        Log.e("image", "" + items?.ImageId)


        when (holder) {

            is LeftViewHolder -> (holder as LeftViewHolder).bindLeftData(
                Category?.get(position)!!,
                holder
            )
            is RightViewHolder -> (holder as RightViewHolder).bindRightData(
                Category?.get(position)!!,
                holder
            )
        }

        if (onItemClickListener != null) {

            holder.itemView.setOnClickListener {
                onItemClickListener?.onClick(position, items!!)
            }
        }

    }


    override fun getItemCount(): Int {

        return Category?.size ?: 0
    }

    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {

        fun onClick(position: Int, Category: Category)
    }

    fun createLeftSide(parent: ViewGroup): LeftSideCategoryBinding {

        var itemleftSideCategoryBinding: LeftSideCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.left_side_category,
            parent,
            false
        )

        return itemleftSideCategoryBinding

    }

    fun createRightSide(parent: ViewGroup): RightSideCategoryBinding {

        var itemRightSideCategoryBinding: RightSideCategoryBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.right_side_category,
            parent,
            false
        )

        return itemRightSideCategoryBinding

    }

    fun refreashAdapter(category: List<Category?>?) {

        Category = category
        notifyDataSetChanged()
    }
}