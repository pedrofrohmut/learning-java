package com.pedrofrohmut.content_calendar.models;

import java.time.LocalDateTime;

public record Content(Integer id, String title, String description, Status status, ContentType contentType,
        LocalDateTime createdAt, LocalDateTime updatedAt, String url) {
}
