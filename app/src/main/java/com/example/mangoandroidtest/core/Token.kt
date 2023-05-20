package com.example.mangoandroidtest.core

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val userId: Int
)