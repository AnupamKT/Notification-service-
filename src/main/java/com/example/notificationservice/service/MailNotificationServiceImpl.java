package com.example.notificationservice.service;

import com.example.notificationservice.common.InvalidNotificationException;
import com.example.notificationservice.converter.NotificationConverter;
import com.example.notificationservice.dao.EmailDTO;
import com.example.notificationservice.dao.MessageDTO;
import com.example.notificationservice.model.Mail;
import com.example.notificationservice.model.Message;
import com.example.notificationservice.model.Response;
import com.example.notificationservice.repository.EmailRepository;
import com.example.notificationservice.validator.NotificationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class MailNotificationServiceImpl implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationValidator notificationValidator;
    @Autowired
    private NotificationConverter converter;
    @Autowired
    private EmailRepository emailRepository;

    @Override
    public Response send(Message message) throws InvalidNotificationException {
        //validate email request
        notificationValidator.validate(message);
        //send mail in separate thread
        CompletableFuture.runAsync(()->sendEmail(message));
        //store  sent mail info in DB
        storeSentEmailDetailsInDB(message);
        String msg = "email sent successfully";
        return new Response(HttpStatus.OK.value(), msg);
    }

    private void sendEmail(Message message) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            Mail mail = (Mail) message;
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(mail.getSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getSender(), "noreply@gmail.com"));
            mimeMessageHelper.setTo(mail.getReceiver());
            mimeMessageHelper.setText(mail.getMessageBody());
            mailSender.send(mimeMessageHelper.getMimeMessage());
            log.info("mail sent successfully to {} from {}", mail.getReceiver(), mail.getSender());
        } catch (Exception ex) {
            log.error("error occurred while invoking send API of java mail sender");
        }
    }

    private void storeSentEmailDetailsInDB(Message message) {
        //call converter method to get EMailDTO
        MessageDTO messageDTO = converter.convert(message);
        //call repo method to persist EmailDTO
        EmailDTO emailDTO = (EmailDTO) messageDTO;
        emailRepository.save(emailDTO);
    }
}
