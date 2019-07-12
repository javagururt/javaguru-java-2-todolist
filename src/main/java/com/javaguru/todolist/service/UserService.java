package com.javaguru.todolist.service;

import com.javaguru.todolist.domain.Task;
import com.javaguru.todolist.domain.User;
import com.javaguru.todolist.repository.HibernateUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class UserService {

    private final HibernateUserRepository userRepository;

    @Autowired
    public UserService(HibernateUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long createUser(User user) {
        return userRepository.save(user);
    }

    public User findUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found, id: " + userId));
    }

    public void addTaskToUser(Task task, Long userId) {
        User user = findUserById(userId);
        user.getTasks().add(task);
        userRepository.update(user);
    }
}
