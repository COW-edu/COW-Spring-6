package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users") // 필수는 아니지만 있으면 편함. // 동사가 들어가지 않는다.
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    //jason으로 요청이 옴.
    @PostMapping // 유저 생성하는 것. 자동으로 json -> DTO 생성
    public void createUser(@RequestBody UserDto userDto) {
        userService.addUser(userDto);
    }

    @GetMapping
    public List<UserDto> getUsers(){ // 전체 유저 넘겨주는 것.
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody UserDto dto){
        userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
