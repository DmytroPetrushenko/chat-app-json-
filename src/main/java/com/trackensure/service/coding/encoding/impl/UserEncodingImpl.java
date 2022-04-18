package com.trackensure.service.coding.encoding.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trackensure.dto.response.ResponseUserDto;
import com.trackensure.lib.Service;
import com.trackensure.service.coding.encoding.UserEncoding;

@Service
public class UserEncodingImpl implements UserEncoding {

    @Override
    public String toJson(ResponseUserDto responseUserDto) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(responseUserDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't encoding ResponseUserDto: "
                    + responseUserDto + " to json!", e);
        }
    }
}
