package com.task1.domain.model


data class ArticlesItemDTO(

    val publishedAt: String? = null,

    val author: String? = null,

    val urlToImage: String? = null,

    val description: String? = null,

    val source: SourceDTO? = null,

    val title: String? = null,

    val url: String? = null,

    val content: String? = null
)

data class NewsResponseDTO(

    val totalResults: Int? = null,

    val articles: List<ArticlesItemDTO?>? = null,

    val status: String? = null,

    //in fail

    val code: String? = null,

    val message: String? = null
)

data class SourceDTO(

    val name: String? = null,

    val id: String? = null
)

