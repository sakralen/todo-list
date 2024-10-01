package com.example.todolist.service.impl;

import com.example.todolist.dto.subscriber.SubscriberCreateUpdateDto;
import com.example.todolist.dto.subscriber.SubscriberReadDto;
import com.example.todolist.entity.Subscriber;
import com.example.todolist.mapper.subscriber.SubscriberCreateUpdateDtoMapper;
import com.example.todolist.mapper.subscriber.SubscriberReadDtoMapper;
import com.example.todolist.repository.SubscriberRepository;
import com.example.todolist.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    private final PasswordEncoder passwordEncoder;

    private final SubscriberCreateUpdateDtoMapper subscriberCreateUpdateDtoMapper;

    private final SubscriberReadDtoMapper subscriberReadDtoMapper;

    @Override
    public Optional<SubscriberReadDto> findById(Long id) {
        return subscriberRepository.findById(id)
                .map(subscriberReadDtoMapper::toDto);
    }

    @Override
    public List<SubscriberReadDto> findAll() {
        return subscriberRepository.findAll().stream()
                .map(subscriberReadDtoMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public Optional<SubscriberReadDto> create(SubscriberCreateUpdateDto createDto) {
        return Optional.of(createDto)
                .map(dto -> {
                    Subscriber subscriber = Subscriber.builder()
                            .email(dto.email())
                            .firstName(dto.firstName())
                            .lastName(dto.lastName())
                            .password(passwordEncoder.encode(dto.password()))
                            .build();

                    return subscriberRepository.save(subscriber);
                })
                .map(subscriberReadDtoMapper::toDto);
    }

    @Override
    @Transactional
    public Optional<SubscriberReadDto> update(Long id, SubscriberCreateUpdateDto updateDto) {
        return subscriberRepository.findById(id)
                .map(subscriber -> {
                    subscriber.setFirstName(updateDto.firstName());
                    subscriber.setLastName(updateDto.lastName());
                    subscriber.setEmail(updateDto.email());
                    subscriber.setPassword(updateDto.password());

                    return subscriberRepository.saveAndFlush(subscriber);
                })
                .map(subscriberReadDtoMapper::toDto);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        return subscriberRepository.findById(id)
                .map(subscriber -> {
                    subscriberRepository.delete(subscriber);
                    subscriberRepository.flush();

                    return true;
                })
                .orElse(false);
    }

    @Override
    public Optional<Subscriber> findByEmail(String email) {
        return subscriberRepository.findByEmail(email);
    }

}
