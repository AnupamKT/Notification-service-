package com.example.notificationservice.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@Data
@NoArgsConstructor
public class MessageDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String sender;
    private String receiver;
    private String subject;
    @Lob
    private String mailContent;
}
