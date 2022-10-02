package com.task1.domain.repos.sourcesRepository

import com.task1.domain.model.SourcesItemDTO

interface SourcesRepo {

    suspend fun getSources(category: String): List<SourcesItemDTO?>?
}