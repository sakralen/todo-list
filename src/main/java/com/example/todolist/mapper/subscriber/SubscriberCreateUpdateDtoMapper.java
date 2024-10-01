package com.example.todolist.mapper.subscriber;

import com.example.todolist.dto.subscriber.SubscriberCreateUpdateDto;
import com.example.todolist.entity.Subscriber;
import com.example.todolist.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriberCreateUpdateDtoMapper extends BaseMapper<Subscriber, SubscriberCreateUpdateDto> {
}
