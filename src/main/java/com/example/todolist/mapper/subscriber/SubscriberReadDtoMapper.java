package com.example.todolist.mapper.subscriber;

import com.example.todolist.dto.subscriber.SubscriberReadDto;
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
public interface SubscriberReadDtoMapper extends BaseMapper<Subscriber, SubscriberReadDto> {

    @Override
    @Mapping(source = "createdTasks", target = "createdTasksIds", qualifiedByName = "tasksToIds")
    @Mapping(source = "assignedTasks", target = "assignedTasksIds", qualifiedByName = "tasksToIds")
    SubscriberReadDto toDto(Subscriber subscriber);

    @Named("tasksToIds")
    static List<Long> tasksToIds(List<Task> tasks) {
        return tasks.stream()
                .map(Task::getId)
                .toList();
    }

}
