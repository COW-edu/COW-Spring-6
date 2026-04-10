package com.example.controller;

import com.example.entity.Post;
import com.example.service.PostService;
import com.example.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")

public class PostController {
    private final PostService postService;

    @PostMapping
    public Long createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping
    public List<PostDto> getPosts(){
        return postService.findAllPosts().stream().map(PostDto::fromEntity).toList();
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable Long postId){
        Post post = postService.getPostById(postId);
        return PostDto.fromEntity(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long postId, @RequestParam Long authorId){
        postService.deletePost(postId, authorId);
    }

}
