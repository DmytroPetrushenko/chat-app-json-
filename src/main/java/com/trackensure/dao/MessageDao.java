package com.trackensure.dao;

import com.trackensure.model.Message;
import java.util.List;

public interface MessageDao {

    List<Message> getLastFiftyMessages();

    Message save(Message message);
}
