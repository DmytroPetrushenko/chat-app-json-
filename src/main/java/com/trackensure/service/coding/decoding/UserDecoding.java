package com.trackensure.service.coding.decoding;

import com.trackensure.dto.request.RequestUserDto;

public interface UserDecoding {

    RequestUserDto fromJson(String jsonRequestUserDto);
}
