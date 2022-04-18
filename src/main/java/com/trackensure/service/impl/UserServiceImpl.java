package com.trackensure.service.impl;

import com.trackensure.dao.UserDao;
import com.trackensure.lib.Inject;
import com.trackensure.lib.Service;
import com.trackensure.model.User;
import com.trackensure.service.UserService;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User logging(User user) {
        Optional<User> optionalUser = userDao.getByLogin(user.getLogin());
        if (optionalUser.isEmpty()) {
            return userDao.save(user);
        }
        return optionalUser.get();
    }

    @Override
    public User getByLogin(String login) {
        Optional<User> optionalUser = userDao.getByLogin(login);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Optional user is empty! Can't get user by login: "
                    + login + " from DB!");
        }
        return optionalUser.get();

    }
}
