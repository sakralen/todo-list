package com.example.todolist.mapper.task;

import com.example.todolist.dto.task.TaskCreateUpdateDto;
import com.example.todolist.entity.Task;
import com.example.todolist.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskCreateUpdateDtoMapper extends BaseMapper<Task, TaskCreateUpdateDto> {
}
