package com.kishan.user_service.repository;

import com.kishan.user_service.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        users.add(new User(1, "Kishan", "kishan@gmail.com"));
        users.add(new User(2, "Rahul", "rahul@gmail.com"));
        users.add(new User(3, "Aman", "aman@gmail.com"));
    }

    public List<User> getUsers() {
        return users;
    }

    public String addUser(User user) {
        users.add(user);
        return "User Added Successfully";
    }

    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
    }

    public Optional<User> updateUser(int id, User updatedUser) {
        Optional<User> existing = getUserById(id);
        existing.ifPresent(u -> {
            u.setName(updatedUser.getName());
            u.setEmail(updatedUser.getEmail());
        });
        return existing;
    }

    public boolean deleteUser(int id) {
        return users.removeIf(u -> u.getId() == id);
    }
}