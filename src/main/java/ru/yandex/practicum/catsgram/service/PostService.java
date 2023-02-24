package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.PostNotFoundException;
import ru.yandex.practicum.catsgram.exception.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static Integer globalId = 0;
    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll(Integer size, Integer from, String sort) {
        return posts.stream().sorted((post1, post2) -> {
            int compare = post1.getCreationDate().compareTo(post2.getCreationDate()); //прямой порядок сортировки
            if(sort.equals("desc"))
                compare = -1 * compare; //обратный порядок сортировки
            return compare;
        }).skip(from).limit(size).collect(Collectors.toList());
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
