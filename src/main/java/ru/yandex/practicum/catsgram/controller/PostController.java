package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.List;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/posts")
    public List<Post> findAll() {
        return postService.findAll();
    }

    @GetMapping("/post/{postId}")
    public Post findPost(@PathVariable("postId") int postId) {
        return postService.findPostById(postId);
    }

    @PostMapping(value = "/post")
    public void create(@RequestBody Post post) {
        postService.create(post);
    }
}
