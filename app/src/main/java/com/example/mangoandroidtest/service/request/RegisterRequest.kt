package com.example.mangoandroidtest.service.request

import lombok.Builder
import lombok.Data

@Data
@Builder
class RegisterRequest(
    val phone: String,
    val name: String,
    val username: String
)
