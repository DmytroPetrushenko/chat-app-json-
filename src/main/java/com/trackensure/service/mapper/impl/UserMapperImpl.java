package com.trackensure.service.mapper.impl;

import com.trackensure.dto.request.RequestUserDto;
import com.trackensure.dto.response.ResponseUserDto;
import com.trackensure.lib.Service;
import com.trackensure.model.User;
import com.trackensure.service.mapper.UserMapper;

@Service
public class UserMapperImpl implements UserMapper {
    @Override
    public User fromDto(RequestUserDto requestUserDto) {
        User user = new User();
        user.setLogin(requestUserDto.getLogin());
        return user;
    }

    @Override
    public ResponseUserDto toDto(User user) {
        ResponseUserDto responseUserDto = new ResponseUserDto();
        responseUserDto.setId(user.getId());
        responseUserDto.setLogin(user.getLogin());
        return responseUserDto;
    }
}
