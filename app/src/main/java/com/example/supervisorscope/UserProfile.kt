package com.example.supervisorscope

data class UserProfile(
    val id: Int,
    val name: String,
    val email: String
)

data class UserPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)