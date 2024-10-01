package com.example.todolist.security.service.impl;

import com.example.todolist.entity.Subscriber;
import com.example.todolist.entity.Task;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.security.service.TaskPermissionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
//@Service
@Transactional(readOnly = true)
public class TaskPermissionServiceImpl implements TaskPermissionService {

    private final TaskRepository taskRepository;

    public boolean isCreatorOrAssignee(Long taskId, String subscriberEmail) {
        return isCreator(taskId, subscriberEmail)
                || isAssignee(taskId, subscriberEmail);
    }

    // TODO: Эксепшены.
    public boolean isCreator(Long taskId, String subscriberEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        return task.getCreatedBy().getEmail().equals(subscriberEmail);
    }

    public boolean isAssignee(Long taskId, String subscriberEmail) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        return task.getAssignedTo().stream()
                .map(Subscriber::getEmail)
                .anyMatch(subscriberEmail::equals);
    }

}
