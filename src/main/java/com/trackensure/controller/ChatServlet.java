package com.trackensure.controller;

import com.trackensure.dto.request.RequestMessageDto;
import com.trackensure.dto.response.ResponseMessageDto;
import com.trackensure.lib.Injector;
import com.trackensure.model.Message;
import com.trackensure.service.MessageService;
import com.trackensure.service.coding.decoding.MessageDecoding;
import com.trackensure.service.coding.encoding.MessageEncoding;
import com.trackensure.service.mapper.MessageMapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChatServlet extends HttpServlet {
    private final Injector injector = Injector.getInstance("com.trackensure");
    private final MessageService messageService = (MessageService) injector
            .getInstance(MessageService.class);
    private final MessageEncoding messageEncoding = (MessageEncoding) injector
            .getInstance(MessageEncoding.class);
    private final MessageDecoding messageDecoding = (MessageDecoding) injector
            .getInstance(MessageDecoding.class);
    private final MessageMapper messageMapper = (MessageMapper) injector
            .getInstance(MessageMapper.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/chat.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestMessageDto requestMessageDto = messageDecoding
                .fromJson(req.getParameter("encodedJsonMessage"));
        Message message = messageService.save(messageMapper.fromDto(requestMessageDto));

        List<ResponseMessageDto> messageDtoList = messageService.getLastFiftyMessages().stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());

        resp.getWriter().write(messageEncoding.toJson(messageMapper
                .toChatDto(message.getUser().getLogin(), messageDtoList)));
    }
}
