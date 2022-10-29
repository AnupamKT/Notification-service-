package com.example.notificationservice.validator;

import com.example.notificationservice.common.InvalidNotificationException;
import com.example.notificationservice.model.Mail;
import com.example.notificationservice.model.Message;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
@Component
public class EmailNotificationValidatorImpl implements NotificationValidator {
    @Override
    public void validate(Message message) throws InvalidNotificationException {
        boolean isValidEmail = true;
        Mail mail = (Mail) message;
        //validate if sender and receiver is not null
        checkMandatoryFields(mail);
        //validate emailCC address format
        if (StringUtils.hasText(mail.getMailCc())) {
            if (!validateEmailAddressFormat(mail.getMailCc())) {
                isValidEmail = false;
            }
        }
        //validate emailCC address format
        if (StringUtils.hasText(mail.getMailBcc())) {
            if (!validateEmailAddressFormat(mail.getMailBcc())) {
                isValidEmail = false;
            }
        }

        if (!isValidEmail) {
            String msg = "either CC or BCC email address is not in correct format";
            throw new InvalidNotificationException(msg);
        }
    }

    private boolean validateEmailAddressFormat(String email) {
        return EmailValidator.getInstance().isValid(email);
    }

    private void checkMandatoryFields(Mail mail) throws InvalidNotificationException {
        boolean isValidaEmail = false;
        if (StringUtils.hasText(mail.getReceiver())
                && StringUtils.hasText(mail.getSender())
                && validateEmailAddressFormat(mail.getReceiver())
                && validateEmailAddressFormat(mail.getSender())) {
            isValidaEmail = true;
        }
        if (!isValidaEmail) {
            //throw exception
            String msg = "either sender or receiver email address is null or not in correct format";
            throw new InvalidNotificationException(msg);
        }
    }
}
