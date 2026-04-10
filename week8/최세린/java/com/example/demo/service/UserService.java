package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Post;
import com.example.demo.entity.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public UserService(UserRepository userRepository,
                       PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public UserDto create(UserDto dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .build();

        User saved = userRepository.save(user);
        return new UserDto(saved.getId(), saved.getName(), saved.getEmail());
    }

    public User update(Long id, UserDto dto) {
        User u = userRepository.findById(id).orElseThrow();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        return userRepository.save(u);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserDto> list() {
        return userRepository.findAll().stream()
                .map(u -> new UserDto(u.getId(), u.getName(), u.getEmail()))
                .toList();
    }

    public UserDto get(Long id) {
        User u = userRepository.findById(id).orElseThrow();
        return new UserDto(u.getId(), u.getName(), u.getEmail());
    }

    public Post addPost(Long userId, Post post) {
        User u = userRepository.findById(userId).orElseThrow();
        post.setUser(u);
        return postRepository.save(post);
    }
}
