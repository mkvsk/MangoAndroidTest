package com.example.mangoandroidtest.service.request

class UserUpdateRequest(
    val name: String,
    val username: String,
    val birthday: String = "",
    val city: String? = "",
    val vk: String? = "",
    val instagram: String? = "",
    val status: String? = "",
    val avatar: UserUpdateAvatarRequest
)

class UserUpdateAvatarRequest(
    val filename: String = "",
    val base_64: String = "",
)