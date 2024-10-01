package com.example.todolist.validation.annotation;

import com.example.todolist.validation.TaskStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskStatusValidator.class)
public @interface ValidTaskStatus {

    String message() default "Невалидный статус";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
