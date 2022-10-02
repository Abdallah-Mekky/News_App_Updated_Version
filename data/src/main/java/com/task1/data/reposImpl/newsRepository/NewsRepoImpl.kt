package com.task1.data.reposImpl.newsRepository


import com.task1.domain.model.ArticlesItemDTO
import com.task1.domain.model.SourcesItemDTO
import com.task1.domain.repos.newsRepository.NewsOnlineDataSource
import com.task1.domain.repos.newsRepository.NewsRepo
import java.lang.Exception
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepo {
    override suspend fun getNewsBySource(
        source: SourcesItemDTO,
        query: String?
    ): List<ArticlesItemDTO?>? {

        try {

            var result = newsOnlineDataSource.getNewsBySource(source, query ?: "")

            return result

        } catch (ex: Exception) {
            throw ex
        }
    }
}