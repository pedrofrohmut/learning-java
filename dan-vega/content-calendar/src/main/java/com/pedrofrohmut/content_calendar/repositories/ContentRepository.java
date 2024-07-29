package com.pedrofrohmut.content_calendar.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.pedrofrohmut.content_calendar.models.Content;

public interface ContentRepository extends ListCrudRepository<Content, Integer> {}
