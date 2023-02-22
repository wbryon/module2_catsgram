package ru.yandex.practicum.catsgram.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

@Data
public class User {
    @Email
    private String email;
    private String nickname;
    private LocalDate birthdate;

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
