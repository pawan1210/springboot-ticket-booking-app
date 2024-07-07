package com.example.ticketbookingapp.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import com.example.ticketbookingapp.model.User;

@Data
public class AddUserDto {
    @NotEmpty(message = "user name is required")
    private String name;

    public User toUser() {
        return new User().setName(this.name);
    }
}
