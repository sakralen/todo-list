package com.example.todolist.dto.subscriber;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record SubscriberCreateUpdateDto(

        @NotNull
        @Pattern(regexp = "^[А-Я][а-я]{1,63}$",
                message = "Имя должно начинаться с заглавной буквы и содержать не более 64 букв")
        String firstName,

        @Pattern(regexp = "^[А-Я][а-я]{1,63}$",
                message = "Фамилия должна начинаться с заглавной буквы и содержать не более 64 букв")
        @NotNull
        String lastName,

        @NotNull
        @Email(message = "Электронная почта должна быть валидной")
        String email,

        @NotNull
        @NotBlank
        String password

) {
}
