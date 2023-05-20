package com.example.mangoandroidtest.core

import com.example.mangoandroidtest.core.Avatars

data class User(
    val name: String,
    val username: String,
//    TODO date
    val birthday: String = "",
    val city: String? = "",
    val vk: String? = "",
    val instagram: String? = "",
    val status: String? = "",
    val avatar: String? = "",
    val id: Int,
    val last: String? = "",
    val online: Boolean = false,
    val created: String? = "",
    val phone: String? = "",
    val completedTask: Int?,
    val avatars: Avatars? = null
)