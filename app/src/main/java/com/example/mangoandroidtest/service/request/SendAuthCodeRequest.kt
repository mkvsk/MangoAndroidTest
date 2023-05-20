package com.example.mangoandroidtest.service.request

import lombok.Builder
import lombok.Data

@Data
@Builder
class SendAuthCodeRequest(var phone: String = "")