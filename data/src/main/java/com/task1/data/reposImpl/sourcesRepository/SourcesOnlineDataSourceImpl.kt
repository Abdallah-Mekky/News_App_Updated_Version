package com.task1.data.reposImpl.sourcesRepository

import com.task1.data.Constants
import com.task1.data.Utils.convertToDTOClass
import com.task1.data.api.WepServices
import com.task1.domain.model.SourcesItemDTO
import com.task1.domain.model.SourcesResponseDTO
import com.task1.domain.repos.sourcesRepository.SourcesOnlineDataSource
import java.lang.Exception
import javax.inject.Inject

class SourcesOnlineDataSourceImpl @Inject constructor(val wepServices: WepServices) : SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {

        try {
            var result = wepServices.getNewsSources(Constants.apiKey, category)

            return result.convertToDTOClass(SourcesResponseDTO::class.java).sources

        } catch (ex: Exception) {
            throw ex
        }
    }
}