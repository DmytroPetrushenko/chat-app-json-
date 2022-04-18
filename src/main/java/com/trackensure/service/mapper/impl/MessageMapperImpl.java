package com.trackensure.service.mapper.impl;

import com.trackensure.dto.request.RequestMessageDto;
import com.trackensure.dto.response.ResponseChatDto;
import com.trackensure.dto.response.ResponseMessageDto;
import com.trackensure.lib.Inject;
import com.trackensure.lib.Service;
import com.trackensure.model.Message;
import com.trackensure.service.UserService;
import com.trackensure.service.mapper.MessageMapper;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageMapperImpl implements MessageMapper {

    @Inject
    private UserService userService;

    @Override
    public ResponseMessageDto toDto(Message message) {
        ResponseMessageDto responseMessageDto = new ResponseMessageDto();
        responseMessageDto.setLogin(message.getUser().getLogin());
        responseMessageDto.setText(message.getText());
        responseMessageDto.setTimeStamp(message.getTimeStamp());
        return responseMessageDto;
    }

    @Override
    public ResponseChatDto toChatDto(String login, List<ResponseMessageDto> messageDtoList) {
        ResponseChatDto responseChatDto = new ResponseChatDto();
        responseChatDto.setLogin(login);
        responseChatDto.setMessageDtoList(messageDtoList);
        return responseChatDto;
    }

    @Override
    public Message fromDto(RequestMessageDto requestMessageDto) {
        Message message = new Message();
        message.setUser(userService.getByLogin(requestMessageDto.getLogin()));
        message.setText(requestMessageDto.getText());
        message.setTimeStamp(LocalDateTime.now().toString());
        return message;
    }
}
