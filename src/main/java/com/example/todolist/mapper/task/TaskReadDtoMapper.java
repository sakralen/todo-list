package com.example.todolist.mapper.task;

import com.example.todolist.dto.task.TaskReadDto;
import com.example.todolist.entity.Subscriber;
import com.example.todolist.entity.Task;
import com.example.todolist.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaskReadDtoMapper extends BaseMapper<Task, TaskReadDto> {

    @Override
    @Mapping(source = "createdBy", target = "creatorId", qualifiedByName = "creatorToId")
    @Mapping(source = "assignedTo", target = "assignedSubscribersIds", qualifiedByName = "assigneesToIds")
    TaskReadDto toDto(Task task);

    @Named("creatorToId")
    static Long creatorToId(Subscriber creator) {
        return creator.getId();
    }

    @Named("assigneesToIds")
    static List<Long> assigneesToIds(List<Subscriber> assignees) {
        return assignees.stream()
                .map(Subscriber::getId)
                .toList();
    }

}
