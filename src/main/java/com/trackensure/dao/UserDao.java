package com.trackensure.dao;

import com.trackensure.model.User;
import java.util.Optional;

public interface UserDao {
    Optional<User> getByLogin(String login);

    User save(User user);
}
