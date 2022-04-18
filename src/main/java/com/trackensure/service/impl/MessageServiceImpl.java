package com.trackensure.service.impl;

import com.trackensure.dao.MessageDao;
import com.trackensure.lib.Inject;
import com.trackensure.lib.Service;
import com.trackensure.model.Message;
import com.trackensure.service.MessageService;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Inject
    private MessageDao messageDao;

    @Override
    public List<Message> getLastFiftyMessages() {
        return messageDao.getLastFiftyMessages();
    }

    @Override
    public Message save(Message message) {
        return messageDao.save(message);
    }
}
