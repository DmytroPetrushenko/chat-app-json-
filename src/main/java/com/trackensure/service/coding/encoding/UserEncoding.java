package com.trackensure.service.coding.encoding;

import com.trackensure.dto.response.ResponseUserDto;

public interface UserEncoding {

    String toJson(ResponseUserDto responseUserDto);
}
