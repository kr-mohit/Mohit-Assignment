package com.example.androidassignment.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        val token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX2lkIjoiZmMwMjRiYjItMzFiNi00MTY4LTlkMjItMTRjMjdkY2JjYzUyIiwicGhvbmUiOiI4ODk2MzI4MTY2IiwiZXhwIjoxNzM1ODEyMjUzfQ.5W3VRVaAMWBojts9Td9VF7HQrIY6xfsb63kb6i2YQUg"

        request.addHeader("TOKEN", token)

        return chain.proceed(request.build())
    }
}