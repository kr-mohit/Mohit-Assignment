package com.example.androidassignment.data.remote

import com.example.androidassignment.data.model.TrendingPostsResponseDTO
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface MainAPI {

    @POST("api/v1/homepage/trendingpostsgroupedv3/")
    suspend fun getTrendingPosts(
        @Body requestBody: RequestBody
    ): Response<TrendingPostsResponseDTO>
}