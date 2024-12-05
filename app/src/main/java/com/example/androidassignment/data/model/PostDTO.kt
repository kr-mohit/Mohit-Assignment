package com.example.androidassignment.data.model

import com.example.androidassignment.data.utils.DataUtils.toTimeAgo
import com.example.androidassignment.domain.model.Post

data class PostDTO(
    val brand: List<BrandDTO>?,
    val bucket_status: String?,
    val handle: String?,
    val interest_id: String?,
    val is_followed: Boolean?,
    val is_liked: Boolean?,
    val is_sponsored: Int?,
    val language: String?,
    val likes: Int?,
    val live_at: String?,
    val max_down_res: String?,
    val mentions: Int?,
    val mux_assest_id: String?,
    val mux_playback_id: String?,
    val mux_status: String?,
    val order: Int?,
    val origin: String?,
    val post_id: String?,
    val products: List<Any>?,
    val profile_pic: String?,
    val resh: String?,
    val resw: String?,
    val tag_text: String?,
    val tags: String?,
    val text: String?,
    val title: String?,
    val user_id: String?,
    val views: Int?
)

fun PostDTO.toDomainPost(): Post {
    return Post(
        imageUrl = this.mux_playback_id?.let { "https://image.mux.com/$it/thumbnail.png" } ?: "",
        profileImageUrl = this.profile_pic ?: "",
        username = this.handle ?: "",
        title = this.title ?: "",
        timeAgo = this.live_at?.toTimeAgo() ?: ""
    )
}