package com.example.todolist.controller.advice;

import com.example.todolist.dto.exception.ValidationExceptionDto;
import com.example.todolist.dto.exception.ValidationExceptionDto.Violation;
import com.example.todolist.exception.TaskAlreadyAssignedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDto onMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<Violation> violations = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {
            violations.add(new Violation(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return new ValidationExceptionDto(violations);
    }


    // TODO: Обернуть в дто.
    @ExceptionHandler(TaskAlreadyAssignedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onTaskAlreadyAssignedException(TaskAlreadyAssignedException exception) {
        return exception.getMessage();
    }

}
