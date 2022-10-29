package com.example.notificationservice.controller;

import com.example.notificationservice.common.InvalidNotificationException;
import com.example.notificationservice.model.Mail;
import com.example.notificationservice.model.Response;
import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/email")
    public ResponseEntity sendEmail(@RequestBody Mail mail) throws InvalidNotificationException {
        Response response = notificationService.send(mail);
        return ResponseEntity.ok(response);
    }

}
