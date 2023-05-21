package com.example.mangoandroidtest.network.response

class CheckAuthCodeResponse(
    val refresh_token: String = "",
    val access_token: String = "",
    val user_id: Int? = null,
    val is_user_exists: Boolean = false
)