package com.example.todolist.dto.subscriber;

import java.util.List;

public record SubscriberReadDto(

        Long id,

        String firstName,

        String lastName,

        String email,

        List<Long> createdTasksIds,

        List<Long> assignedTasksIds

) {
}
