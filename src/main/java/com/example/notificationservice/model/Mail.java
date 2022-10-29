package com.example.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail extends Message{
    private String mailCc;
    private String mailBcc;
    private String contentType;
    private List< Object > attachments;

}
