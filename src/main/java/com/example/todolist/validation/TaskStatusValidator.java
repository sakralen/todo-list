package com.example.todolist.validation;

import com.example.todolist.entity.TaskStatus;
import com.example.todolist.validation.annotation.ValidTaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class TaskStatusValidator implements ConstraintValidator<ValidTaskStatus, String> {

    private final List<String> validStatuses = Arrays.stream(TaskStatus.values())
            .map(TaskStatus::toString)
            .toList();

    @Override
    public boolean isValid(String payload, ConstraintValidatorContext constraintValidatorContext) {
        return validStatuses.contains(payload);
    }

}
