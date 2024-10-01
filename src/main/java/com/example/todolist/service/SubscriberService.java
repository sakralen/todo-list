package com.example.todolist.service;

import com.example.todolist.dto.subscriber.SubscriberCreateUpdateDto;
import com.example.todolist.dto.subscriber.SubscriberReadDto;
import com.example.todolist.entity.Subscriber;

import java.util.List;
import java.util.Optional;

public interface SubscriberService {

    Optional<SubscriberReadDto> findById(Long id);

    List<SubscriberReadDto> findAll();

    Optional<SubscriberReadDto> create(SubscriberCreateUpdateDto createDto);

    Optional<SubscriberReadDto> update(Long id, SubscriberCreateUpdateDto updateDto);

    boolean delete(Long id);

    Optional<Subscriber> findByEmail(String email);

}
