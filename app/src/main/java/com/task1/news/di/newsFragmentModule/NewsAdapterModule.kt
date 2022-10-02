package com.task1.news.di.newsFragmentModule

import com.task1.news.news.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class NewsAdapterModule {

    @Provides
    fun provideNewsAdapter(): NewsAdapter {

        return NewsAdapter(null)
    }
}