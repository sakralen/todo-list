package com.example.todolist.dto.task;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskCreateUpdateDto(

        @NotNull
        Long creatorId,

        @NotNull
        @NotBlank(message = "Поле не должно быть пустым")
        String title,

        @NotNull
        @NotBlank(message = "Поле не должно быть пустым")
        String description,

        @NotNull
        @Future(message = "Время окончания не может быть в прошлом")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime dueDate

) {
}
