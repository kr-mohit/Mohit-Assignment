package com.example.androidassignment.data.repository

import com.example.androidassignment.data.model.toDomainTrendingHashtags
import com.example.androidassignment.data.remote.MainAPI
import com.example.androidassignment.data.utils.DataUtils.toJsonRequestBody
import com.example.androidassignment.domain.model.TrendingHashtag
import com.example.androidassignment.domain.model.TrendingPostsRequest
import com.example.androidassignment.domain.repository.MainRepository
import com.example.androidassignment.utils.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val api: MainAPI
): MainRepository {
    override suspend fun getTrendingPosts(trendingPostsRequest: TrendingPostsRequest): Response<List<TrendingHashtag>> {
        val requestBody = trendingPostsRequest.toMap().toJsonRequestBody()
        return try {
            val response = api.getTrendingPosts(requestBody)
            if (response.isSuccessful) {
                val trendingHashtags = response.body()?.toDomainTrendingHashtags()
                Response.Success(trendingHashtags)
            } else {
                Response.Error(response.message())
            }
        } catch (e: Exception) {
            Response.Error(e.message)
        }
    }

    private fun TrendingPostsRequest.toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["language"] = language
        map["interest_id"] = interestId
        map["brand_id"] = brandId
        map["force_refresh"] = forceRefresh
        map["start_pos"] = startPos
        map["end_pos"] = endPos
        return map
    }
}