package com.example.ticketbookingapp.service;

import com.example.ticketbookingapp.model.User;
import com.example.ticketbookingapp.repository.UserRepository;

import java.util.UUID;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(User user) throws Exception {
        String userId = UUID.randomUUID().toString();

        user.setId(userId);

        userRepository.addUser(user);
    }

    @Override
    public User findUser(String userId) {
        return userRepository.findUser(userId);
    }

    @Override
    public Map<String, User> getAllUsers() {
        return userRepository.getAllUsers();
    }
}
