package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.User;
import java.util.Map;

public interface UserService {
    void addUser(User user) throws Exception;

    User findUser(String userId);

    Map<String, User> getAllUsers();
}
