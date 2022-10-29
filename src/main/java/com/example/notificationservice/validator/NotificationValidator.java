package com.example.notificationservice.validator;

import com.example.notificationservice.common.InvalidNotificationException;
import com.example.notificationservice.model.Message;
import org.springframework.stereotype.Component;

@Component
public interface NotificationValidator {

    void validate(Message message) throws InvalidNotificationException;
}
