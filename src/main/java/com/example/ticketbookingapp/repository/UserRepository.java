package com.example.ticketbookingapp.repository;

import java.util.Map;

import com.example.ticketbookingapp.exceptions.UserAlreadyExistsException;
import com.example.ticketbookingapp.model.User;

public interface UserRepository {
    void addUser(User user) throws UserAlreadyExistsException;

    User findUser(String name);

    Map<String, User> getAllUsers();
}
