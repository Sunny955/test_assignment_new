package com.assignment.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UsersResponse {
    List<UserDto> userDtoList;
    Integer pageNumber;
    Integer pageSize;
    long totalElements;
    int totalPages;
    boolean isLast;
}
