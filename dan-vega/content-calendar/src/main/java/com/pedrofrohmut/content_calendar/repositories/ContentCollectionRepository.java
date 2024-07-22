package com.pedrofrohmut.content_calendar.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.pedrofrohmut.content_calendar.models.Content;
import com.pedrofrohmut.content_calendar.models.Status;
import com.pedrofrohmut.content_calendar.models.ContentType;

import jakarta.annotation.PostConstruct;

@Repository
public class ContentCollectionRepository {
    private final List<Content> contentList;

    public ContentCollectionRepository() {
        this.contentList = new ArrayList<Content>();
    }

    @PostConstruct
    private void init() {
        Content c1 = new Content(1, "Post One", "Post One Description", Status.IDEA, ContentType.ARTICLE, LocalDateTime.now(), null, "");
        contentList.add(c1);
    }

    public List<Content> findAll() {
        return contentList;
    }

    public Optional<Content> findById(Integer contentId) {
        return contentList.stream().filter(x -> x.id().equals(contentId)).findFirst();
    }

	public void save(Content content) {
        contentList.add(content);
	}

    public void update(Integer id, Content content) {
        var found = findById(id);
        if (found.isPresent()) {
            var index = contentList.indexOf(found.get());
            contentList.removeIf(x -> x.id().equals(id));
            contentList.add(index, content);
        }
    }

    public void delete(Integer id) {
        contentList.removeIf(x -> x.id().equals(id));
    }
}
