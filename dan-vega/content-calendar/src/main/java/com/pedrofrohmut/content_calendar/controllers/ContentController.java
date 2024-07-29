package com.pedrofrohmut.content_calendar.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.pedrofrohmut.content_calendar.models.Content;
import com.pedrofrohmut.content_calendar.repositories.ContentCollectionRepository;
import com.pedrofrohmut.content_calendar.repositories.ContentRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/content")
@CrossOrigin
public class ContentController {

    private final ContentRepository contentRepository;

    public ContentController(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    @GetMapping
    public List<Content> findAll() {
        return contentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Content findById(@PathVariable Integer id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found"));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void create(@Valid @RequestBody Content content) {
        contentRepository.save(content);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Content content) {
        var found = contentRepository.findById(id);
        if (!found.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        //contentRepository.update(id, content);
        contentRepository.save(found.get());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        var found = contentRepository.findById(id);
        if (!found.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found");
        }
        //contentRepository.delete(id);
        contentRepository.delete(found.get());
    }
}
