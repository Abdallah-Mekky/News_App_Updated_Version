package com.task1.news.News

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task1.news.Api.ApiManager
import com.task1.news.category.Category
import com.task1.news.Constants
import com.task1.news.model.ArticlesItem
import com.task1.news.model.SourcesItem
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel : ViewModel() {


    val sourcesLiveData = MutableLiveData<List<SourcesItem?>?>()
    val progressBarLiveData = MutableLiveData<Boolean>()
    val newsLiveData = MutableLiveData<List<ArticlesItem?>?>()

    fun getNewsSources(Category: Category) {

        viewModelScope.launch {

            progressBarLiveData.value = true

            try {

                val sourcesOfNews =
                    ApiManager.getApis().getNewsSources(Constants.apiKey, Category.id)
                progressBarLiveData.value = false
                sourcesLiveData.value = sourcesOfNews.sources

            } catch (ex: Exception) {

                progressBarLiveData.value = false
            }
        }
    }

    fun getNewsBySource(sourceClicked: SourcesItem?, query: String?) {


        viewModelScope.launch {

            try {

                val news = ApiManager.getApis()
                    .getNewsBySource(Constants.apiKey, sourceClicked?.id ?: "", query ?: "")
                newsLiveData.value = news.articles

            } catch (ex: Exception) {

                Log.e("error",ex.localizedMessage)

            }
        }


    }
}