package com.example.todolist.mapper;

public interface BaseMapper<E, DTO> {

    E toEntity(DTO dto);

    DTO toDto(E entity);

}
