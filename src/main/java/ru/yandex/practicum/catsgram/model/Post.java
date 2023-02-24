package ru.yandex.practicum.catsgram.model;

import java.time.Instant;

public class Post {
    private Integer id;
    private final String author; // автор
    private final Instant creationDate = Instant.now(); // дата создания
    private final String description; // описание
    private final String photoUrl; // url-адрес фотографии

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public Post(String author, String description, String photoUrl) {
        this.author = author;
        this.description = description;
        this.photoUrl = photoUrl;
    }
}
