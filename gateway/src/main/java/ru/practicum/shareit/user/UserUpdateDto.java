package ru.practicum.shareit.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateDto {
    @Size(max = 255, message = "Имя не может быть длиннее 255 символов")
    private String name;

    @Email(message = "Некорректный email", regexp = ".+@.+\\..+")
    @Size(max = 512, message = "Email не может быть длиннее 512 символов")
    private String email;
}