package com.trackensure.service.mapper;

import com.trackensure.dto.request.RequestUserDto;
import com.trackensure.dto.response.ResponseUserDto;
import com.trackensure.model.User;

public interface UserMapper {

    User fromDto(RequestUserDto requestUserDto);

    ResponseUserDto toDto(User user);
}
