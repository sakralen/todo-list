package com.example.todolist.exception;

// TODO: Дополнить эксепшен полями задачи и юзера.
public class TaskAlreadyAssignedException extends RuntimeException {

    public TaskAlreadyAssignedException(String message) {
        super(message);
    }

}
