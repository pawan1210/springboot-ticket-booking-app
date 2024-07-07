package com.example.ticketbookingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ticketbookingapp.dto.AddUserDto;
import com.example.ticketbookingapp.dto.ApiResponse;
import com.example.ticketbookingapp.exceptions.UserAlreadyExistsException;
import com.example.ticketbookingapp.model.User;
import com.example.ticketbookingapp.service.UserService;

import jakarta.validation.Valid;

import java.util.Map;

@RestController()
@RequestMapping("/v1/internal/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public ResponseEntity<?> addUser(@Valid @RequestBody AddUserDto addUserDto, BindingResult bindingResult) {
        ApiResponse<User> response = new ApiResponse<>();

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, bindingResult.getFieldError().getDefaultMessage()));
        }

        try {
            User user = addUserDto.toUser();

            this.userService.addUser(user);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response.constructResponse(user, null));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response.constructResponse(null, e.getMessage()));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findUser(@PathVariable String userId) {
        ApiResponse<User> response = new ApiResponse<>();

        if (userId == null || userId.length() == 0) {
            return ResponseEntity.badRequest().body(response.constructResponse(null, "userId is required"));
        }

        User user = userService.findUser(userId);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response.constructResponse(null, "user not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(user, null));
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUsers() {
        ApiResponse<Map<String, User>> response = new ApiResponse<>();
        Map<String, User> users = userService.getAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(response.constructResponse(users, null));
    }
}
