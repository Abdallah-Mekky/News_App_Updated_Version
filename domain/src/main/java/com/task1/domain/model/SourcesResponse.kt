package com.task1.domain.model


data class SourcesResponseDTO(

    val sources: List<SourcesItemDTO?>? = null,

    val status: String? = null,

    //in fail

    val code: String? = null,

    val message: String? = null

)

data class SourcesItemDTO(

    val country: String? = null,

    val name: String? = null,

    val description: String? = null,

    val language: String? = null,

    val id: String? = null,

    val category: String? = null,

    val url: String? = null
)
