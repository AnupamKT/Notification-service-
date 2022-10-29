package com.example.notificationservice.converter;

import com.example.notificationservice.dao.EmailDTO;
import com.example.notificationservice.model.Mail;
import com.example.notificationservice.model.Message;
import org.springframework.stereotype.Component;

@Component
public class EmailNotificationConverterImpl implements NotificationConverter{
    @Override
    public EmailDTO convert(Message message) {
        Mail mail =(Mail)message;
        EmailDTO dto= new EmailDTO();
        dto.setSender(mail.getSender());
        dto.setReceiver(mail.getReceiver());
        dto.setSubject(mail.getSubject());
        dto.setMailCc(mail.getMailCc());
        dto.setMailBcc(mail.getMailBcc());
        dto.setMailContent(mail.getMessageBody());
        return dto;
    }
}
