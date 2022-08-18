package com.task1.news.Api

import com.task1.news.model.NewsResponse
import com.task1.news.model.SourcesResponse
import retrofit2.Call
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