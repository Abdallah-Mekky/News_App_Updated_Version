package com.task1.data.reposImpl.sourcesRepository

import com.task1.domain.model.SourcesItemDTO
import com.task1.domain.repos.sourcesRepository.SourcesOnlineDataSource
import com.task1.domain.repos.sourcesRepository.SourcesRepo
import java.lang.Exception
import javax.inject.Inject

class SourcesRepoImpl @Inject constructor(val sourcesOnlineDataSource: SourcesOnlineDataSource) : SourcesRepo {
    override suspend fun getSources(category: String): List<SourcesItemDTO?>? {

        try {

            var result = sourcesOnlineDataSource.getSources(category)
            return result

        } catch (ex: Exception) {
            throw ex
        }
    }
}