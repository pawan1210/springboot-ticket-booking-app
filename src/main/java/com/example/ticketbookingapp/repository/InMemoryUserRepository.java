package com.example.ticketbookingapp.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.example.ticketbookingapp.exceptions.UserAlreadyExistsException;
import com.example.ticketbookingapp.model.User;


@Repository
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> users;
    private Set<String> names;

    public InMemoryUserRepository() {
        this.users = new HashMap<>();
        this.names = new HashSet<>();
    }

    public void addUser(User user) throws UserAlreadyExistsException {
        if (this.users.containsKey(user.getId()) || this.names.contains(user.getName())) {
            throw new UserAlreadyExistsException("user already exists");
        }

        this.users.put(user.getId(), user);
        this.names.add(user.getName());
    }

    public User findUser(String userId) {
        return this.users.get(userId);
    }

    public Map<String, User> getAllUsers() {
       return this.users;
    }
}
