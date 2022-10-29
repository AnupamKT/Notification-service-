package com.example.notificationservice.converter;

import com.example.notificationservice.dao.MessageDTO;
import com.example.notificationservice.model.Message;
import org.springframework.stereotype.Component;

@Component
public interface NotificationConverter {

    MessageDTO convert(Message message);
}
