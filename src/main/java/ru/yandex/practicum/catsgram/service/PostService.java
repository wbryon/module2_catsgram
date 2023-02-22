package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    String sort;
    int size;
    LocalDate from;
    private static Integer globalId = 0;
    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        if (sort.equals("asc")) {}
        if (sort.equals("desc")) {}
        return posts;
    }

    private static Integer getNextId() {
        return globalId++;
    }

    public Post findPostById(int postId) {
        return posts.stream()
                .filter(post -> post.getId() == postId).findFirst()
                .orElseThrow(() -> new PostNotFoundException("Пост № " + postId + " не найден"));
    }

    public Post create(Post post) {
        User postAuthor = userService.findUserByEmail(post.getAuthor());
        if (postAuthor == null)
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        post.setId(getNextId());
        posts.add(post);
        return post;
    }
}
