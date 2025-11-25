package com.example.service;

import com.example.entity.User;
import com.example.dto.UserDto;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor // 생성자 주입을 자동으로 해줌. private finally를 대신 입력해야 함. // autowired를 하면 그래도 생성자까진 만들어야 했는데 얘 하면 생성자 없이 가능함. 생성자 만들면 안됨.
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public void addUser(UserDto userDto) {
        // DTO -> Entity -> Entity로 저장
        User user = userDto.toEntity(); // entity변환

        userRepository.save(user); // entity 저장
    }

    public List<UserDto> getAllUsers(){

        return userRepository.findAll().stream().map(UserDto::from).collect(Collectors.toList());
    }
//repository한테 받아!

    public UserDto getUser(Long id) {
        User user = userRepository.findById(id);
        return UserDto.from(user);
    }

    @Transactional
    public void updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id);
        user.update(userDto.getName(), userDto.getEmail());
    }

    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id);
        userRepository.delete(user);
    }
}
