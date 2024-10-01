package com.example.todolist.security.service.impl;

import com.example.todolist.service.SubscriberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final SubscriberService subscriberService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return subscriberService.findByEmail(email)
                .map(subscriber -> new User(subscriber.getEmail(), subscriber.getPassword(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

}
