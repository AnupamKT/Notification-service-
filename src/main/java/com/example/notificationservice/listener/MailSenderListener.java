package com.example.notificationservice.listener;

import com.example.notificationservice.controller.NotificationController;
import com.example.notificationservice.model.Mail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "kafkaMailSender",
        groupId = "mailSenderGroup",
        containerFactory = "getKafkaConsumerFactory")
@Slf4j
public class MailSenderListener {
    @Autowired
    private NotificationController controller;

    @KafkaHandler
    public void updateInventory(Mail mail) {
        try {
            controller.sendEmail(mail);
        } catch (Exception e) {
            log.error("error {} occurred in MailSenderListener while sending mail for {} ",
                    e.getMessage(), mail.getReceiver());
        }
    }
}
