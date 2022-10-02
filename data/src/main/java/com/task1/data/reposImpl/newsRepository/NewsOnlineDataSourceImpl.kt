package com.task1.data.reposImpl.newsRepository

import com.task1.data.Constants
import com.task1.data.Utils.convertToDTOClass
import com.task1.data.api.WepServices
import com.task1.domain.model.ArticlesItemDTO
import com.task1.domain.model.NewsResponseDTO
import com.task1.domain.model.SourcesItemDTO
import com.task1.domain.repos.newsRepository.NewsOnlineDataSource
import java.lang.Exception
import javax.inject.Inject

class NewsOnlineDataSourceImpl @Inject constructor(val wepServices: WepServices) : NewsOnlineDataSource {

    override suspend fun getNewsBySource(
        source: SourcesItemDTO,
        query: String?
    ): List<ArticlesItemDTO?>? {

        try {
            var result = wepServices.getNewsBySource(Constants.apiKey, source.id!!, query ?: "")

            return result.convertToDTOClass(NewsResponseDTO::class.java).articles
        } catch (ex: Exception) {

            throw ex
        }
    }
}