package com.assignment.test.dtos;

import com.assignment.test.entities.UserRole;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private Integer userId;

    private String name;

    private String email;

    private UserRole role;

    private String message;
}
