package com.example.todolist.dto.task;

import com.example.todolist.entity.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record TaskReadDto(

        Long id,

        String title,

        String description,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime dueDate,

        TaskStatus status,

        Long creatorId,

        List<Long> assignedSubscribersIds

) {
}
