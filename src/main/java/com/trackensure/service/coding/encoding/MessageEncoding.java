package com.trackensure.service.coding.encoding;

import com.trackensure.dto.response.ResponseChatDto;

public interface MessageEncoding {

    String toJson(ResponseChatDto responseChatDto);
}
