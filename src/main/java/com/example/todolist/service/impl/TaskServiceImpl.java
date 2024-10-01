package com.example.todolist.service.impl;

import com.example.todolist.dto.task.TaskCreateUpdateDto;
import com.example.todolist.dto.task.TaskReadDto;
import com.example.todolist.dto.task.TaskStatusUpdateDto;
import com.example.todolist.entity.Subscriber;
import com.example.todolist.entity.Task;
import com.example.todolist.entity.TaskStatus;
import com.example.todolist.exception.TaskAlreadyAssignedException;
import com.example.todolist.mapper.task.TaskReadDtoMapper;
import com.example.todolist.repository.SubscriberRepository;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.example.todolist.entity.TaskStatus.PENDING;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final SubscriberRepository subscriberRepository;

    private final TaskReadDtoMapper taskReadDtoMapper;

    @Override
    public Optional<TaskReadDto> findById(Long id) {
        return taskRepository.findById(id)
                .map(taskReadDtoMapper::toDto);
    }

    @Override
    public List<TaskReadDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskReadDtoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Optional<TaskReadDto> create(TaskCreateUpdateDto createDto) {
        return subscriberRepository.findById(createDto.creatorId())
                .map(creator -> {
                    Task task = Task.builder()
                            .createdBy(creator)
                            .title(createDto.title())
                            .description(createDto.description())
                            .dueDate(createDto.dueDate())
                            .status(PENDING)
                            .build();

                    return taskRepository.save(task);
                })
                .map(taskReadDtoMapper::toDto);
    }

    @Override
    @Transactional
    public Optional<TaskReadDto> update(Long id, TaskCreateUpdateDto updateDto) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(updateDto.title());
                    task.setDescription(updateDto.description());
                    task.setDueDate(updateDto.dueDate());

                    return taskRepository.saveAndFlush(task);
                })
                .map(taskReadDtoMapper::toDto);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    taskRepository.flush();

                    return true;
                })
                .orElse(false);
    }

    @Override
    @Transactional
    public Optional<TaskReadDto> updateStatus(Long id, TaskStatusUpdateDto statusUpdateDto) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setStatus(TaskStatus.valueOf(statusUpdateDto.status()));

                    return taskRepository.saveAndFlush(task);
                })
                .map(taskReadDtoMapper::toDto);
    }

    // TODO: Сделать свои эксепшены и хэндлеры.
    @Override
    @Transactional
    public Optional<TaskReadDto> assign(Long taskId, Long assigneeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Task not found"));

        Subscriber assignee = subscriberRepository.findById(assigneeId)
                .orElseThrow(() -> new EntityNotFoundException("Assignee not found"));

        assignTaskToAssignee(task, assignee);

        Task savedTask = taskRepository.save(task);

        return Optional.of(taskReadDtoMapper.toDto(savedTask));
    }

    private void assignTaskToAssignee(Task task, Subscriber assignee) {
        if (assignee.getAssignedTasks().contains(task)
                || task.getAssignedTo().contains(assignee)) {
            throw new TaskAlreadyAssignedException("Задача уже присвоена пользователю");
        }

        assignee.getAssignedTasks().add(task);
        task.getAssignedTo().add(assignee);
    }

}
