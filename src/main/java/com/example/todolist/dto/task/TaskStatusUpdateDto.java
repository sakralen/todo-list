package com.example.todolist.dto.task;

import com.example.todolist.validation.annotation.ValidTaskStatus;
import jakarta.validation.constraints.NotNull;

public record TaskStatusUpdateDto(

        @NotNull
        @ValidTaskStatus
        String status

) {
}
