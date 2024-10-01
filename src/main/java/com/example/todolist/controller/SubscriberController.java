package com.example.todolist.controller;

import com.example.todolist.dto.subscriber.SubscriberCreateUpdateDto;
import com.example.todolist.dto.subscriber.SubscriberReadDto;
import com.example.todolist.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/subscribers")
@RequiredArgsConstructor
public class SubscriberController {

    private final SubscriberService subscriberService;

    @GetMapping("/{id}")
    public SubscriberReadDto findById(@PathVariable Long id) {
        return subscriberService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public List<SubscriberReadDto> findAll() {
        return subscriberService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubscriberReadDto create(@RequestBody @Validated SubscriberCreateUpdateDto createDto) {
        return subscriberService.create(createDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @PutMapping("/{id}")
    public SubscriberReadDto update(@PathVariable Long id, @RequestBody @Validated SubscriberCreateUpdateDto updateDto) {
        return subscriberService.update(id, updateDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!subscriberService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}

