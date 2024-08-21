package com.assignment.test.utils;

import com.assignment.test.dtos.UserDto;
import com.assignment.test.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMappers {
    public UserDto repoToDto(User u) {
        return UserDto.builder()
                .userId(u.getUserId())
                .email(u.getEmail())
                .name(u.getName())
                .role(u.getRole())
                .build();
    }

    public User dtoToRepo(UserDto u) {
        return User.builder()
                .email(u.getEmail())
                .name(u.getName())
                .role(u.getRole())
                .build();
    }
}
