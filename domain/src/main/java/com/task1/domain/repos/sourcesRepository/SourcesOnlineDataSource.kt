package com.task1.domain.repos.sourcesRepository

import com.task1.domain.model.SourcesItemDTO


interface SourcesOnlineDataSource {

    suspend fun getSources(category: String): List<SourcesItemDTO?>?
}