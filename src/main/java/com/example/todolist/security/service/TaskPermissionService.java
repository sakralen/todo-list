package com.example.todolist.security.service;

public interface TaskPermissionService {

    boolean isCreatorOrAssignee(Long taskId, String subscriberEmail);

    boolean isCreator(Long taskId, String subscriberEmail);

    boolean isAssignee(Long taskId, String subscriberEmail);

}
