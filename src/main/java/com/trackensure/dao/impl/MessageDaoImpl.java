package com.trackensure.dao.impl;

import com.trackensure.dao.MessageDao;
import com.trackensure.lib.Dao;
import com.trackensure.lib.Inject;
import com.trackensure.model.Message;
import com.trackensure.model.User;
import com.trackensure.service.UserService;
import com.trackensure.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

@Dao
public class MessageDaoImpl implements MessageDao {
    @Inject
    private UserService userDao;

    @Override
    public List<Message> getLastFiftyMessages() {
        String query = "SELECT m.id, m.message, m.time_stamp, u.id, u.login FROM messages m "
                + "JOIN users u on u.id = m.user_id ORDER BY m.id DESC LIMIT 50";
        try (Connection connection = ConnectionUtil.getConnection();
                Statement getLastFiftyMessageStatement = connection.createStatement()) {
            ResultSet resultSet = getLastFiftyMessageStatement.executeQuery(query);
            List<Message> messageList = new LinkedList<>();
            while (resultSet.next()) {
                messageList.add(getMessageFromResultSet(resultSet));
            }
            return messageList;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get last 50 messages from DB!", e);
        }
    }

    @Override
    public Message save(Message message) {
        String query = "INSERT INTO messages (user_id, message, time_stamp) VALUES (?, ?, ?);";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement saveMessageStatement = connection
                        .prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            saveMessageStatement.setObject(1, message.getUser().getId());
            saveMessageStatement.setString(2, message.getText());
            saveMessageStatement.setString(3, message.getTimeStamp());
            saveMessageStatement.executeUpdate();
            ResultSet resultSet = saveMessageStatement.getGeneratedKeys();
            if (resultSet.next()) {
                message.setId(resultSet.getObject(1, Long.class));
            }
            return message;
        } catch (SQLException e) {
            throw new RuntimeException("Can't save the message: "
                    + message + " in DB!", e);
        }
    }

    private Message getMessageFromResultSet(ResultSet resultSet) {
        Message message = new Message();
        try {
            message.setId(resultSet.getObject("m.id", Long.class));
            User user = new User();
            user.setId(resultSet.getObject("u.id", Long.class));
            user.setLogin(resultSet.getString("u.login"));
            message.setUser(user);
            message.setText(resultSet.getString("m.message"));
            message.setTimeStamp(resultSet.getString("m.time_stamp"));
            return message;
        } catch (SQLException e) {
            throw new RuntimeException("Can't get a message from ResultSet: "
                    + resultSet + " !", e);
        }
    }
}
