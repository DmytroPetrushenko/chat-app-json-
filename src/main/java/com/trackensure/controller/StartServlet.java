package com.trackensure.controller;

import com.trackensure.dto.request.RequestUserDto;
import com.trackensure.dto.response.ResponseMessageDto;
import com.trackensure.lib.Injector;
import com.trackensure.model.User;
import com.trackensure.service.MessageService;
import com.trackensure.service.UserService;
import com.trackensure.service.coding.decoding.UserDecoding;
import com.trackensure.service.coding.encoding.MessageEncoding;
import com.trackensure.service.mapper.MessageMapper;
import com.trackensure.service.mapper.UserMapper;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StartServlet extends HttpServlet {
    private final Injector injector = Injector.getInstance("com.trackensure");
    private final UserService userService = (UserService) injector.getInstance(UserService.class);
    private final UserMapper userMapper = (UserMapper) injector.getInstance(UserMapper.class);
    private final UserDecoding userDecoding = (UserDecoding) injector
            .getInstance(UserDecoding.class);
    private final MessageService messageService = (MessageService) injector
            .getInstance(MessageService.class);
    private final MessageEncoding messageEncoding = (MessageEncoding) injector
            .getInstance(MessageEncoding.class);
    private final MessageMapper messageMapper = (MessageMapper) injector
            .getInstance(MessageMapper.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String jsonRequestUserDto = req.getParameter("jsonRequestUserDto");
        RequestUserDto requestUserDto = userDecoding.fromJson(jsonRequestUserDto);
        User user = userMapper.fromDto(requestUserDto);
        String login = userService.logging(user).getLogin();
        HttpSession session = req.getSession();
        session.setAttribute("login", login);

        List<ResponseMessageDto> messageDtoList = messageService.getLastFiftyMessages().stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
        resp.getWriter().write(messageEncoding.toJson(messageMapper
                .toChatDto(login, messageDtoList)));
    }
}
