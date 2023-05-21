package com.example.mangoandroidtest.network.request

import lombok.Builder
import lombok.Data

@Data
@Builder
class SendAuthCodeRequest(var phone: String = "")