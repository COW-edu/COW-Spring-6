package com.example.demo.controller;


import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto){
        userService.addUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers(){
        return userService.getAllUsers();
    }
}
