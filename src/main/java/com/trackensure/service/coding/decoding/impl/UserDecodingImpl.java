package com.trackensure.service.coding.decoding.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackensure.dto.request.RequestUserDto;
import com.trackensure.lib.Service;
import com.trackensure.service.coding.decoding.UserDecoding;

@Service
public class UserDecodingImpl implements UserDecoding {

    @Override
    public RequestUserDto fromJson(String jsonRequestUserDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonRequestUserDto, RequestUserDto.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't decode jsonRequestUserDto: "
                    + jsonRequestUserDto + " from json!");
        }
    }
}
