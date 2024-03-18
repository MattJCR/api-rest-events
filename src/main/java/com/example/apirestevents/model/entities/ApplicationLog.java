package com.example.apirestevents.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "ApplicationLogs")
@Getter
@Setter
public class ApplicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(length = 50, nullable = false)
    private String severity;

    @Column(length = 50)
    private String category;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    @Column(length = 255)
    private String userId;

    @Column(length = 50)
    private String clientIp;

    @Column(length = 100)
    private String location;

    @Column(length = 255)
    private String url;

    @Column(columnDefinition = "TEXT")
    private String exceptionData;

    @Column(columnDefinition = "TEXT")
    private String additionalInfo;

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();
    }
}
