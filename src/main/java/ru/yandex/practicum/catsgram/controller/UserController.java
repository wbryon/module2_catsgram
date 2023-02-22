package ru.yandex.practicum.catsgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;

import java.util.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{email}")
    public Optional <User> findByEmail(@PathVariable String email) {
        return userService.findAll().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        userService.validate(user);
        userService.save(user);
        return user;
    }

    @PutMapping
    public User put(@RequestBody User user) {
        userService.validate(user);
        userService.update(user);
        return user;
    }
}
