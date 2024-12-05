package com.example.androidassignment.domain.usecase

import com.example.androidassignment.domain.model.TrendingHashtag
import com.example.androidassignment.domain.model.TrendingPostsRequest
import com.example.androidassignment.domain.repository.MainRepository
import com.example.androidassignment.utils.Response
import javax.inject.Inject

class GetTrendingPostsUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke(): Response<List<TrendingHashtag>> {
        val trendingPostsRequest = TrendingPostsRequest(
            language = listOf("Gujarati"),
            interestId = listOf(17, 18),
            brandId = 9,
            forceRefresh = true,
            startPos = 0,
            endPos = 1000
        )
        return repository.getTrendingPosts(trendingPostsRequest)
    }
}