package com.assignment.test.services;

import com.assignment.test.dtos.UserDto;
import com.assignment.test.dtos.UsersResponse;
import com.assignment.test.entities.User;
import com.assignment.test.repositories.UserRepository;
import com.assignment.test.utils.UserMappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMappers userMappers;

    public UserServiceImpl(UserRepository userRepository, UserMappers userMappers) {
        this.userRepository = userRepository;
        this.userMappers = userMappers;
    }

    @Override
    public UsersResponse getAllUsers(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        Page<User> userPage = userRepository.findAll(pageable);

        List<User> users = userPage.getContent();

        List<UserDto> response = users
                .stream()
                .map(userMappers::repoToDto)
                .toList();

        return new UsersResponse(response,pageNumber,pageSize,userPage.getTotalElements(),userPage.getTotalPages(),userPage.isLast());
    }

    @Override
    public User addUser(User u) {
        userRepository.save(u);
        return u;
    }

    @Override
    public void deleteUser(User u) {
        userRepository.delete(u);
    }

    @Override
    public User modifyUser(User u) {
        Optional<User> user = userRepository.findById(u.getUserId());

        if(user.isPresent()) {
            userRepository.save(u);
        }

        return null;
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(Integer.valueOf(id));
    }


}
