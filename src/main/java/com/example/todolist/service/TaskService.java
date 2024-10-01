package com.example.todolist.service;

import com.example.todolist.dto.task.TaskCreateUpdateDto;
import com.example.todolist.dto.task.TaskReadDto;
import com.example.todolist.dto.task.TaskStatusUpdateDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    Optional<TaskReadDto> findById(Long id);

    List<TaskReadDto> findAll();

    Optional<TaskReadDto> create(TaskCreateUpdateDto createDto);

    Optional<TaskReadDto> update(Long id, TaskCreateUpdateDto updateDto);

    boolean delete(Long id);

    Optional<TaskReadDto> updateStatus(Long id, TaskStatusUpdateDto statusUpdateDto);

    Optional<TaskReadDto> assign(Long taskId, Long assigneeId);

}
