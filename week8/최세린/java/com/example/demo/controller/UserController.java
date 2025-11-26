package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.Post;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) { this.service = service; }

    @PostMapping
    public UserDto create(@RequestBody UserDto user) {
        return service.create(user);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestBody UserDto user) {
        service.update(id, user);
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<UserDto> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        return service.get(id);
    }

    @PostMapping("/{id}/posts")
    public Post addPost(@PathVariable Long id, @RequestBody Post post) {
        return service.addPost(id, post);
    }
}
