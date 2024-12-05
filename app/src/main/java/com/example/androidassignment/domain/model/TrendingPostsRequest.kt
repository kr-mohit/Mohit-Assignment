package com.example.androidassignment.domain.model

data class TrendingPostsRequest(
    val language: List<String> = emptyList(),
    val interestId: List<Int> = emptyList(),
    val brandId: Int = 0,
    val forceRefresh: Boolean = false,
    val startPos: Int = 0,
    val endPos: Int = 0
)