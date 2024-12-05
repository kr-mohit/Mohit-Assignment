package com.example.androidassignment.data.model

import com.example.androidassignment.domain.model.TrendingHashtag

data class TrendingPostsResponseDTO(
    val data: DataDTO?,
    val status: Int?,
    val success: Boolean?
)

fun TrendingPostsResponseDTO.toDomainTrendingHashtags(): List<TrendingHashtag> {
    val trendingHashtagList = mutableListOf<TrendingHashtag>()
    this.data?.let { data ->
        data.meta?.forEachIndexed { index, meta ->
            trendingHashtagList.add(TrendingHashtag(
                meta.tag_text ?: "",
                data.posts?.get(index)?.map { it.toDomainPost() } ?: emptyList())
            )
        }
    }
    return trendingHashtagList
}