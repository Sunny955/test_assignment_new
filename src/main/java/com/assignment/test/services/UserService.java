package com.assignment.test.services;

import com.assignment.test.dtos.UsersResponse;
import com.assignment.test.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UsersResponse getAllUsers(Integer pageNumber, Integer pageSize);
    public User addUser(User u);
    public void deleteUser(User u);
    public User modifyUser(User u);
    public Optional<User> findById(String id);
}
