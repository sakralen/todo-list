package com.example.todolist.repository;

import com.example.todolist.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

    Optional<Subscriber> findByEmail(String email);

}
