package com.example.mangoandroidtest.core

data class User(
    var name: String = "",
    var username: String = "",
    var birthday: String = "",
    var city: String = "",
    var vk: String = "",
    var instagram: String = "",
    var status: String = "",
    val avatar: String = "",
    val id: Int = 0,
    val last: String = "",
    val online: Boolean = false,
    val created: String = "",
    var phone: String = "",
    val completedTask: Int = 0,
    val avatars: Avatars? = null
)