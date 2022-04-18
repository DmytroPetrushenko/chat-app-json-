package com.trackensure.service.coding.encoding.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackensure.dto.response.ResponseChatDto;
import com.trackensure.lib.Service;
import com.trackensure.service.coding.encoding.MessageEncoding;

@Service
public class MessageEncodingImpl implements MessageEncoding {

    @Override
    public String toJson(ResponseChatDto responseChatDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(responseChatDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't encoding ResponseChatDto: "
                    + responseChatDto + " to json format!", e);
        }
    }
}
