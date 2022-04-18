package com.trackensure.service;

import com.trackensure.model.User;

public interface UserService {
    User logging(User user);

    User getByLogin(String login);
}
