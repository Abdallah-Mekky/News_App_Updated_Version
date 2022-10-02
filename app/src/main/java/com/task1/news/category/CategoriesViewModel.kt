package com.task1.news.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task1.news.R

class CategoriesViewModel : ViewModel() {

    private val categoryMutableLiveData = MutableLiveData<List<Category>>()
    val categoryLiveData: LiveData<List<Category>> = categoryMutableLiveData

    fun createCategoryList() {

        categoryMutableLiveData.value = listOf(
            Category("sports", R.drawable.sports, "Sports", R.color.sports_Category),
            Category("technology", R.drawable.politics, "Technology", R.color.technology_Category),
            Category("health", R.drawable.health, "Health", R.color.health_Category),
            Category("business", R.drawable.bussines, "Business", R.color.business_Category),
            Category("general", R.drawable.environment, "General", R.color.general_Category),
            Category("science", R.drawable.science, "Science", R.color.science_Category)
        )
    }
}