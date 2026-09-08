package com.kishan.user_service.service;

import com.kishan.user_service.exception.UserNotFoundException;
import com.kishan.user_service.model.User;
import com.kishan.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.getUsers();
    }

    public String addUser(User user) {
        return userRepository.addUser(user);
    }

    public User getUserById(int id) {
        return userRepository.getUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public User updateUser(int id, User updatedUser) {
        return userRepository.updateUser(id, updatedUser)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public void deleteUser(int id) {
        boolean removed = userRepository.deleteUser(id);
        if (!removed) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
    }
}