package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.User;
import com.javaguru.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found, id: " + userId));
    }

}
