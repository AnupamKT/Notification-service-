package com.example.notificationservice.service;

import com.example.notificationservice.common.InvalidNotificationException;
import com.example.notificationservice.model.Message;
import com.example.notificationservice.model.Response;
import org.springframework.stereotype.Service;

@Service
public interface NotificationService {
    Response send(Message message) throws InvalidNotificationException;
}
