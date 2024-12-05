package com.example.androidassignment.domain.repository

import com.example.androidassignment.domain.model.TrendingHashtag
import com.example.androidassignment.domain.model.TrendingPostsRequest
import com.example.androidassignment.utils.Response

interface MainRepository {

    suspend fun getTrendingPosts(trendingPostsRequest: TrendingPostsRequest): Response<List<TrendingHashtag>>
}