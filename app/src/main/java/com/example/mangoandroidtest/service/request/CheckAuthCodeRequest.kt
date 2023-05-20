package com.example.mangoandroidtest.service.request

import lombok.Builder
import lombok.Data

@Data
@Builder
data class CheckAuthCodeRequest(
    var phone: String = "",
    var code: String = ""
)