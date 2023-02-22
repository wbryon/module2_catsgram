package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exception.InvalidEmailException;
import ru.yandex.practicum.catsgram.exception.UserAlreadyExistException;
import ru.yandex.practicum.catsgram.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();


    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findUserByEmail(String email) {
        if(email == null)
            return null;
        return users.get(email);
    }

    public void save(User user) {
        if(users.containsKey(user.getEmail()))
            throw new UserAlreadyExistException("Пользователь с электронной почтой " +
                    user.getEmail() + " уже зарегистрирован.");
        users.put(user.getEmail(), user);
    }

    public void update(User user) {
        users.put(user.getEmail(), user);
    }

    public void validate(User user) {
        if(user.getEmail() == null || user.getEmail().isBlank())
            throw new InvalidEmailException("Адрес электронной почты не может быть пустым.");
    }
}
