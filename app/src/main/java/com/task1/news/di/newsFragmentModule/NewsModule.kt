package com.task1.news.di.newsFragmentModule

import com.task1.news.news.NewsDetailsFragment
import com.task1.news.news.NewsFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NewsModule {

    @Provides
    fun provideNewsDetailsFragment(): NewsDetailsFragment {

        return NewsDetailsFragment()
    }

    @Provides
    fun provideNewsFragment(): NewsFragment {

        return NewsFragment()
    }
}