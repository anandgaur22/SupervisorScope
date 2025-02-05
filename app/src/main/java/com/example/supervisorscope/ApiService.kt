package com.example.supervisorscope

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users/{id}")
    suspend fun getUserProfile(@Path("id") userId: Int): UserProfile

    @GET("posts")
    suspend fun getUserPosts(@Query("userId") userId: Int): List<UserPost>
}