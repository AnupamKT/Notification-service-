package com.example.notificationservice.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="EMAIL_DETAILS")
@Data
@NoArgsConstructor
public class EmailDTO extends MessageDTO {
    private String mailCc;
    private String mailBcc;



}
