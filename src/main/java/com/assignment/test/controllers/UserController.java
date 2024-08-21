package com.assignment.test.controllers;

import com.assignment.test.dtos.UserDto;
import com.assignment.test.dtos.UsersResponse;
import com.assignment.test.entities.User;
import com.assignment.test.services.UserService;
import com.assignment.test.utils.AppConstants;
import com.assignment.test.utils.UserMappers;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMappers userMappers;

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsersResponse> getAllUsers(
            @RequestParam(defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize
    ) {
        log.debug("Got inside the getAllUsers functions!");
        return ResponseEntity.status(200).body(userService.getAllUsers(pageNumber,pageSize));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> modifyUser(@RequestBody UserDto userDto) {
        User user = userService.modifyUser(userMappers.dtoToRepo(userDto));

        UserDto resp = userMappers.repoToDto(user);

        return ResponseEntity.status(200).body(resp);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Map<String,String>> deleteUser(@PathVariable String id) {
        Optional<User> user = userService.findById(id);

        Map<String,String> resp = new HashMap<>();

        if(user.isPresent()) {
            resp.put("message","User deleted successfully!");
            return ResponseEntity.ok().body(resp);
        }
        resp.put("message","User don't exists with this given Id!");
        return ResponseEntity.badRequest().body(resp);
    }
}
