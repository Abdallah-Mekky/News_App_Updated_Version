package com.task1.domain.repos.newsRepository

import com.task1.domain.model.ArticlesItemDTO
import com.task1.domain.model.SourcesItemDTO


interface NewsRepo {

    suspend fun getNewsBySource(source: SourcesItemDTO, query: String?): List<ArticlesItemDTO?>?
}