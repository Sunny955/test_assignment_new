package com.assignment.test.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class ChangePassword {
    String password;
    String repeatPassword;
}
