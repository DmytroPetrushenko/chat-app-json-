package com.trackensure.service.coding.decoding;

import com.trackensure.dto.request.RequestMessageDto;

public interface MessageDecoding {

    RequestMessageDto fromJson(String encodedJsonMessage);
}
