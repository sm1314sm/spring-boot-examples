package com.neo.service.impl;

import com.neo.domain.Message;
import com.neo.service.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class MessageRepositoryImpl implements MessageRepository {
    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, Message> messages = new ConcurrentHashMap<>();

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>(this.messages.values());
        return messages;
    }

    @Override
    public Message save(Message message) {
        Long id = message.getId();
        if (id == null) {
            id = counter.incrementAndGet();
            message.setId(id);
        }
        messages.put(id, message);
        return message;
    }

    @Override
    public Message update(Message message) {
        messages.put(message.getId(), message);
        return message;
    }

    @Override
    public Message updateText(Message message) {
        Message msg = messages.get(message.getId());
        msg.setText(message.getText());
        messages.put(msg.getId(), msg);
        return msg;
    }

    @Override
    public Message findMessage(Long id) {
        return messages.get(id);
    }

    @Override
    public void deleteMessage(Long id) {
        messages.remove(id);
    }
}
