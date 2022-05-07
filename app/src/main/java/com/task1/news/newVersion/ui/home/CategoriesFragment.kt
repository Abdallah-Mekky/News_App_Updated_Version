package com.task1.news.newVersion.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.task1.news.R
import com.task1.news.newVersion.ui.CategoryAdapter
import com.task1.news.newVersion.ui.category


class CategoriesFragment() : Fragment() {


    lateinit var categories_RecyclerView: RecyclerView
    lateinit var categories_Adapter: CategoryAdapter
    lateinit var category: List<category>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categories_RecyclerView = view.findViewById(R.id.categories_RecyclerView)

        createCategoryList()

        categories_Adapter = CategoryAdapter(category)
        categories_RecyclerView.adapter = categories_Adapter

        categories_Adapter.onItemClickListener = object : CategoryAdapter.OnItemClickListener {
            override fun onClick(position: Int, category: category) {

                onCategoryClick?.onClick(position, category)

            }

        }
    }

    private fun createCategoryList() {

        category = listOf(
            category("sports", R.drawable.sports, "Sports", R.color.sports_Category),
            category("technology", R.drawable.politics, "Technology", R.color.technology_Category),
            category("health", R.drawable.health, "Health", R.color.health_Category),
            category("business", R.drawable.bussines, "Business", R.color.business_Category),
            category("general", R.drawable.environment, "General", R.color.general_Category),
            category("science", R.drawable.science, "Science", R.color.science_Category)
        )
    }


    var onCategoryClick: onCategoryClickListener? = null

    interface onCategoryClickListener {

        fun onClick(position: Int, category: category)
    }


}