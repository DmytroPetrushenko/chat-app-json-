package com.trackensure.dao.impl;

import com.trackensure.dao.UserDao;
import com.trackensure.lib.Dao;
import com.trackensure.model.User;
import com.trackensure.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@Dao
public class UserDaoImpl implements UserDao {

    @Override
    public Optional<User> getByLogin(String login) {
        String query = "SELECT * FROM users WHERE login = ? AND is_deleted = false;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement getByLoginUserStatement = connection.prepareStatement(query)) {
            getByLoginUserStatement.setString(1, login);
            ResultSet resultSet = getByLoginUserStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user by login: "
                    + login + " from DB!");
        }

        return Optional.empty();
    }

    @Override
    public User save(User user) {
        String query = "INSERT INTO users (login) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement saveUserStatement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            saveUserStatement.setString(1, user.getLogin());
            saveUserStatement.executeUpdate();
            ResultSet resultSet = saveUserStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getObject(1, Long.class));
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException("Can't save the user: " + user + " in DB!", e);
        }
    }

    private Optional<User> getUserFromResultSet(ResultSet resultSet) {
        User user = new User();
        try {
            user.setId(resultSet.getObject("id", Long.class));
            user.setLogin(resultSet.getString("login"));
        } catch (SQLException e) {
            throw new RuntimeException("Can't get user parameters from ResultSet: "
                    + resultSet, e);
        }
        return Optional.of(user);
    }
}
