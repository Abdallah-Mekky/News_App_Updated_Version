package com.task1.data.di.sourcesRepoModule

import com.task1.data.api.WepServices
import com.task1.data.reposImpl.sourcesRepository.SourcesOnlineDataSourceImpl
import com.task1.data.reposImpl.sourcesRepository.SourcesRepoImpl
import com.task1.domain.repos.sourcesRepository.SourcesOnlineDataSource
import com.task1.domain.repos.sourcesRepository.SourcesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun provideSourcesOnlineDataSource(
        wepServices: WepServices
    ): SourcesOnlineDataSource {

        return SourcesOnlineDataSourceImpl(wepServices)
    }

    @Provides
    fun provideSourcesRepo(sourcesOnlineDataSource: SourcesOnlineDataSource): SourcesRepo {

        return SourcesRepoImpl(sourcesOnlineDataSource)
    }
}