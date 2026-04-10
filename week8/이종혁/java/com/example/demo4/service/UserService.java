package com.example.demo4.service;

import com.example.demo4.dto.UserDto;
import com.example.demo4.entity.User;
import com.example.demo4.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;

    public void addUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
    }

    public List<UserDto> getAllUsers(){
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto();

            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
