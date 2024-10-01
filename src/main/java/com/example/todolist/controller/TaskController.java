package com.example.todolist.controller;

import com.example.todolist.dto.task.TaskCreateUpdateDto;
import com.example.todolist.dto.task.TaskReadDto;
import com.example.todolist.dto.task.TaskStatusUpdateDto;
import com.example.todolist.security.annotation.CanEditTask;
import com.example.todolist.security.annotation.CanUpdateStatus;
import com.example.todolist.security.service.TaskPermissionService;
import com.example.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    private final TaskPermissionService taskPermissionService;

    @GetMapping("/{id}")
    public TaskReadDto findById(@PathVariable Long id) {
        return taskService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<TaskReadDto> findAll() {
        return taskService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskReadDto create(@RequestBody @Validated TaskCreateUpdateDto createDto) {
        return taskService.create(createDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @CanEditTask
    @PutMapping("/{id}")
    public TaskReadDto update(@PathVariable Long id, @RequestBody @Validated TaskCreateUpdateDto updateDto) {
        return taskService.update(id, updateDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @CanEditTask
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!taskService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @CanUpdateStatus
    @PostMapping("/{id}/status")
    public TaskReadDto updateStatus(@PathVariable Long id, @RequestBody @Validated TaskStatusUpdateDto status) {
        return taskService.updateStatus(id, status)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @CanEditTask
    @PostMapping("/{taskId}/assign/{assigneeId}")
    public TaskReadDto assignTask(@PathVariable Long taskId, @PathVariable Long assigneeId) {
        return taskService.assign(taskId, assigneeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

