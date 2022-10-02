package com.task1.news.di.categoryFragmentModule

import com.task1.news.category.CategoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class)
class CategoryAdapterModule {

    @Provides
    fun provideCategoryAdapter(): CategoryAdapter {

        return CategoryAdapter(null)
    }
}