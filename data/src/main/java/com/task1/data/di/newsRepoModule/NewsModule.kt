package com.task1.data.di.newsRepoModule

import com.task1.data.api.WepServices
import com.task1.data.reposImpl.newsRepository.NewsOnlineDataSourceImpl
import com.task1.data.reposImpl.newsRepository.NewsRepoImpl
import com.task1.domain.repos.newsRepository.NewsOnlineDataSource
import com.task1.domain.repos.newsRepository.NewsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    fun provideNewsOnlineDataSource(
        wepServices: WepServices
    ): NewsOnlineDataSource {

        return NewsOnlineDataSourceImpl(wepServices)
    }

    @Provides
    fun provideNewsRepo(
        newsOnlineDataSource: NewsOnlineDataSource
    ): NewsRepo {

        return NewsRepoImpl(newsOnlineDataSource)
    }
}