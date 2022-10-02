package com.task1.news.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task1.domain.model.ArticlesItemDTO
import com.task1.domain.model.SourcesItemDTO
import com.task1.domain.repos.newsRepository.NewsRepo
import com.task1.domain.repos.sourcesRepository.SourcesRepo
import com.task1.news.category.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    var newsRepository: NewsRepo,
    var sourcesRepository: SourcesRepo
) : ViewModel() {

    private val sourcesMutableLiveData = MutableLiveData<List<SourcesItemDTO?>?>()
    val sourcesLiveData: LiveData<List<SourcesItemDTO?>?> = sourcesMutableLiveData

    private val progressBarMutableLiveData = MutableLiveData<Boolean>()
    val progressBarLiveData: LiveData<Boolean> = progressBarMutableLiveData

    private val newsMutableLiveData = MutableLiveData<List<ArticlesItemDTO?>?>()
    val newsLiveData: LiveData<List<ArticlesItemDTO?>?> = newsMutableLiveData


    fun getNewsSources(category: Category) {

        viewModelScope.launch {

            progressBarMutableLiveData.value = true

            try {

                val sourcesOfNews = sourcesRepository.getSources(category.id)
                progressBarMutableLiveData.value = false
                sourcesMutableLiveData.value = sourcesOfNews

            } catch (ex: Exception) {

                progressBarMutableLiveData.value = false
            }
        }
    }

    fun getNewsBySource(sourceClicked: SourcesItemDTO?, query: String?) {

        viewModelScope.launch {

            try {

                val news = newsRepository.getNewsBySource(sourceClicked!!, query)
                newsMutableLiveData.value = news
            } catch (ex: Exception) {

                Log.e("error", ex.localizedMessage)
            }
        }
    }
}