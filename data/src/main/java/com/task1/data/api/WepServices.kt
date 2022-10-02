package com.task1.data.api

import com.task1.data.model.NewsResponse
import com.task1.data.model.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WepServices {


    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("apiKey") key: String,
        @Query("category") category: String
    ): SourcesResponse

    @GET("v2/everything")
    suspend fun getNewsBySource(
        @Query("apiKey") key: String,
        @Query("sources") source: String,
        @Query("q") query: String
    ): NewsResponse


}