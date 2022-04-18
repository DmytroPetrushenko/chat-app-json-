package com.trackensure.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ResponseChatDto {
    private String login;
    private List<ResponseMessageDto> messageDtoList;
}
