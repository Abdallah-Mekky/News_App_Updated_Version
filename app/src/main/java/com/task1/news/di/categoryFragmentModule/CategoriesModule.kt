package com.task1.news.di.categoryFragmentModule

import com.task1.news.category.CategoriesFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@Module
@InstallIn(ActivityComponent::class)
class CategoriesModule {

    @Provides
    fun provideCategoriesFragment(): CategoriesFragment {

        return CategoriesFragment()
    }
}