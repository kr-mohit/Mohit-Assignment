package com.example.androidassignment.data.utils

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DataUtils {

    fun Map<String, Any>.toJsonRequestBody(): RequestBody {
        val json = JSONObject(this)
        val mediaType = "application/json; charset=utf-8".toMediaType()
        return json.toString().toRequestBody(mediaType)
    }

    fun String.toTimeAgo(): String {
        val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
        val postedDateTime = LocalDateTime.parse(this, formatter)
        val currentTime = LocalDateTime.now()
        val duration = Duration.between(postedDateTime, currentTime)

        return when {
            duration.toDays() > 0 -> {
                "${duration.toDays()} days ago"
            }
            duration.toHours() > 0 -> {
                "${duration.toHours()} hours ago"
            }
            duration.toMinutes() > 0 -> {
                "${duration.toMinutes()} minutes ago"
            }
            else -> "Just now"
        }
    }
}