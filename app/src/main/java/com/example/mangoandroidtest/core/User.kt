package com.example.mangoandroidtest.core

data class User(
    val name: String = "",
    val username: String = "",
    val birthday: String = "",
    val city: String = "",
    val vk: String = "",
    val instagram: String = "",
    val status: String = "",
    val avatar: String = "",
    val id: Int = 0,
    val last: String = "",
    val online: Boolean = false,
    val created: String = "",
    val phone: String = "",
    val completedTask: Int = 0,
    val avatars: Avatars? = null
)