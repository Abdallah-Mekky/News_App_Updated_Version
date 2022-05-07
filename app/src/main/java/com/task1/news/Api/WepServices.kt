package com.task1.news.Api

import com.task1.news.Model.NewsResponse
import com.task1.news.Model.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WepServices {


    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("apiKey") key: String,
        @Query("category") category: String
    ): Call<SourcesResponse>

    @GET("v2/everything")
    fun getNewsBySource(
        @Query("apiKey") key: String,
        @Query("sources") source: String,
        @Query("q") query: String
    ): Call<NewsResponse>


}