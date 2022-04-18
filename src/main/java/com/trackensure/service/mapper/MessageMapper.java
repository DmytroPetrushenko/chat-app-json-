package com.trackensure.service.mapper;

import com.trackensure.dto.request.RequestMessageDto;
import com.trackensure.dto.response.ResponseChatDto;
import com.trackensure.dto.response.ResponseMessageDto;
import com.trackensure.model.Message;
import java.util.List;

public interface MessageMapper {

    ResponseMessageDto toDto(Message message);

    ResponseChatDto toChatDto(String login, List<ResponseMessageDto> messageDtoList);

    Message fromDto(RequestMessageDto requestMessageDto);
}
