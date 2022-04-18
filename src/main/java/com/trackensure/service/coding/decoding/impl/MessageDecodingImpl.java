package com.trackensure.service.coding.decoding.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackensure.dto.request.RequestMessageDto;
import com.trackensure.lib.Service;
import com.trackensure.service.coding.decoding.MessageDecoding;

@Service
public class MessageDecodingImpl implements MessageDecoding {
    @Override
    public RequestMessageDto fromJson(String encodedJsonMessage) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(encodedJsonMessage, RequestMessageDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't decoding encodedJsonMessage: "
                    + encodedJsonMessage + " ! ", e);
        }
    }
}
