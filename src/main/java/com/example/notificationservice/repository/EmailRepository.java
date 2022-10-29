package com.example.notificationservice.repository;

import com.example.notificationservice.dao.EmailDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EmailRepository extends JpaRepository<EmailDTO, UUID> {
}
