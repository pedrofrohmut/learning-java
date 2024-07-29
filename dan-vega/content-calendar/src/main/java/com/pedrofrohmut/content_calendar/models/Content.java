package com.pedrofrohmut.content_calendar.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotBlank;

@Table(value="content")
public record Content(
    @Id
    Integer id,

    @NotBlank
    String title,

    String description,

    Status status,

    @Column("content_type")
    ContentType contentType,

    //@Column("created_at")
    LocalDateTime createdAt,

    //@Column("updated_at")
    LocalDateTime updatedAt,

    String url) {}
