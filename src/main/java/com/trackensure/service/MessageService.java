package com.trackensure.service;

import com.trackensure.model.Message;
import java.util.List;

public interface MessageService {

    List<Message> getLastFiftyMessages();

    Message save(Message message);
}
