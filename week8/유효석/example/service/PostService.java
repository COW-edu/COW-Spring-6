package com.example.service;

import com.example.entity.*;
import com.example.repository.*;
import com.example.dto.PostDto;
import jakarta.persistence.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional()
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createPost(PostDto postDto) {
        //post를 생성하고 생성된 postID를 return 해준다.

        //1. 작성자 존재 유무 파악
        //2. 포스트 생성
        //3. 포스트 정장
        //4. 포스트 아이디 return

        User author = userRepository.findById(postDto.getAuthorId());

        if(author == null) {
            throw new EntityNotFoundException("User not found"); // 작성자를 찾을 수 없다.
        }

        Post post = postDto.toEntity(author);
        postRepository.save(post);

        postRepository.save(post);

        return post.getId();
    }

    public List<Post> findAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long postId) {
        Post post = postRepository.findById(postId);

        if(post == null) {
            throw new EntityNotFoundException("Post not found"); // 찾으려는 postid가 존재하지 않는다.
        }

        return post;
    }
    @Transactional
    public void deletePost(Long postId, Long userId) {
        // 게시글 유무 파악 -> 있다면 author 권한check (admin인지 check -> author id를 비교) -> 권한 ok일 때 삭제

        Post post = getPostById(postId);
        if(post == null) {
            throw new EntityNotFoundException("Post not found");
        }

        User user = userRepository.findById(userId);
        if(user == null) {
            throw new EntityNotFoundException("User not found");
        }

        if(post.getAuthor().equals(user) || post.getAuthor().isAdmin()) {
            postRepository.delete(post);
        }
        else{
            throw new IllegalArgumentException("No authority to delete");
        }
    }

}
